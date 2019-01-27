package ch.skunky.skunklaw.service.impl;

import ch.skunky.skunklaw.model.TagItem;
import ch.skunky.skunklaw.model.TagItemLink;
import ch.skunky.skunklaw.model.TagLink;
import ch.skunky.skunklaw.model.TagTopic;
import ch.skunky.skunklaw.repository.TagItemLinkRepository;
import ch.skunky.skunklaw.repository.TagItemRepository;
import ch.skunky.skunklaw.repository.TagLinkRepository;
import ch.skunky.skunklaw.repository.TagTopicRepository;
import ch.skunky.skunklaw.service.TaggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaggingServiceImpl implements TaggingService {

    @Autowired
    private TagItemRepository tagItemRepository;
    @Autowired
    private TagTopicRepository tagTopicRepository;
    @Autowired
    private TagLinkRepository tagLinkRepository;
    @Autowired
    private TagItemLinkRepository tagItemLinkRepository;


    @Override
    public List<TagTopic> findAllTopics() {
        List<TagTopic> topics = new ArrayList<>();
        tagTopicRepository.findAll().forEach(topics::add);
        topics.sort(Comparator.comparing(TagTopic::getName));
        return topics;
    }

    @Override
    public List<TagTopic> findTopicsLike(String name, String universe) {
        return tagTopicRepository.findTagTopicsByNameContainingAndUniverse(name, universe);
    }

    @Override
    public Optional<TagTopic> findTopic(long id) {
        return tagTopicRepository.findById(id);
    }

    @Override
    public Optional<TagTopic> findTopic(String name, String universe) {
        return tagTopicRepository.findByNameAndUniverse(name, universe);
    }

    @Override
    public Optional<TagItem> findItem(long id) {
        return tagItemRepository.findById(id);
    }

    @Override
    public List<TagTopic> findLinkedTopics(long id) {
        List<TagTopic> topics = new ArrayList<>();
        tagLinkRepository.findAllByTopicId1(id).forEach(
                l -> {
                    tagTopicRepository.findById(l.getTopicId2()).ifPresent(topics::add);
                }
        );
        tagLinkRepository.findAllByTopicId2(id).forEach(
                l -> {
                    tagTopicRepository.findById(l.getTopicId1()).ifPresent(topics::add);
                }
        );

        topics.sort(Comparator.comparing(TagTopic::getName));
        return topics;
    }

    @Override
    public List<TagTopic> findItemLinkedTopics(long id) {
        List<TagTopic> topics = new ArrayList<>();
        tagItemLinkRepository.findAllByItemId(id).forEach(
                l -> {
                    tagTopicRepository.findById(l.getTopicId()).ifPresent(topics::add);
                }
        );
        topics.sort(Comparator.comparing(TagTopic::getName));
        return topics;
    }

    @Override
    public List<TagItem> findLinkedItems(long id) {
        List<TagItem> items = new ArrayList<>();
        tagItemLinkRepository.findAllByTopicId(id).forEach(
                l -> {
                    tagItemRepository.findById(l.getItemId()).ifPresent(items::add);
                }
        );
        items.sort(Comparator.comparing(TagItem::getName));
        return items;
    }

    @Override
    public TagTopic createTopic(TagTopic topic) {
        return tagTopicRepository.save(topic);
    }

    @Override
    public TagTopic updateTopic(TagTopic topic) {
        if(topic.getId()==null) throw new IllegalArgumentException("Updating unsaved topic is not allowed. Call createTopic instead.");
        return tagTopicRepository.save(topic);
    }

    @Override
    public TagItem createItem(TagItem item, String... topics) {
        TagItem savedItem = tagItemRepository.save(item);

        List<TagTopic> allTopics = new ArrayList<>();

        for(String topicName : topics){
            topicName = topicName.trim();

            Optional<TagTopic> topicOptional = tagTopicRepository.findByNameAndUniverse(topicName, item.getUniverse());
            if(topicOptional.isEmpty()){
                // create topic on the fly
                topicOptional = Optional.of(tagTopicRepository.save(new TagTopic(null, topicName, item.getUniverse(), 0)));
            }
            if(topicOptional.isPresent()){
                TagTopic topic = topicOptional.get();
                allTopics.add(topic);

                TagItemLink link = new TagItemLink(null, topic.getId(), savedItem.getId(), 1);
                tagItemLinkRepository.save(link);
            }
        }

        // update links between topics
        for(TagTopic topic1 : allTopics){
            for(TagTopic topic2 : allTopics){
                if(topic1.getId() < topic2.getId()){
                    // already a connection?
                    Optional<TagLink> topicLinkOptional = tagLinkRepository.findByTopicId1AndTopicId2(topic1.getId(), topic2.getId());
                    if(topicLinkOptional.isPresent()){
                        TagLink topicLink = topicLinkOptional.get();
                        topicLink.increaseWeight();
                        tagLinkRepository.save(topicLink);
                    }
                    else{
                        TagLink topicLink = new TagLink(topic1.getId(), topic2.getId());
                        tagLinkRepository.save(topicLink);
                    }
                }
            }
        }

        return savedItem;
    }

    @Override
    public TagItem updateItem(TagItem item) {
        if(item.getId()==null) throw new IllegalArgumentException("Item not yet saved!");
        return tagItemRepository.save(item);
    }

    @Override
    public List<TagItem> search(List<TagTopic> topics, int depth) {

        List<Map<TagTopic, Integer>> topicsMap = new ArrayList<>();
        topics.forEach(
                t -> topicsMap.add(getLinkedTopicsMap(t, depth))
        );

        // only keep topics that are in all lists
        List<TagTopic> theTopics = new ArrayList<>();

        topicsMap.get(0).keySet().forEach(
                t -> {
                    int score = getScore(t, topicsMap);
                    if(score >= 0){
                        t.setWeight(score);
                        theTopics.add(t);
                    }
                }
        );

        theTopics.sort(Comparator.comparingInt(TagTopic::getWeight));

        List<TagItem> items = new ArrayList<>();
        for(TagTopic t : theTopics){
            findLinkedItems(t.getId()).stream()
                    .filter(i -> !items.contains(i))
                    .forEach(items::add);
        }

        return items;
    }

    private int getScore(TagTopic t, List<Map<TagTopic, Integer>> topicsMap) {
        int score = 0;
        for(Map<TagTopic, Integer> topicMap : topicsMap){
            if(topicMap.containsKey(t)) score += topicMap.get(t);
            else return -1;
        }
        return score;
    }

    private Map<TagTopic, Integer> getLinkedTopicsMap(TagTopic center, int depth){

        Map<TagTopic, Integer> topics = new HashMap<>();
        topics.put(center, 0);

        List<TagTopic> outerShell = List.of(center);

        for(int currentDepth = 1; currentDepth < depth; ++currentDepth){
            List<TagTopic> nextOuterShell = new ArrayList<>();
            for(TagTopic topic : outerShell){
                findLinkedTopics(topic.getId()).stream()
                        .filter(t -> !topics.containsKey(t))
                        .forEach(t -> {
                            nextOuterShell.add(t);
                            topics.put(t, depth);
                        });

            }
            outerShell = nextOuterShell;
        }

        return topics;
    }
}
