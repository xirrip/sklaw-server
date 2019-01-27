package ch.skunky.skunklaw.service;

import ch.skunky.skunklaw.model.TagItem;
import ch.skunky.skunklaw.model.TagTopic;

import java.util.List;
import java.util.Optional;

public interface TaggingService {
    List<TagTopic> findAllTopics();
    List<TagTopic> findTopicsLike(String name, String universe);

    Optional<TagTopic> findTopic(long id);
    Optional<TagTopic> findTopic(String name, String universe);

    Optional<TagItem> findItem(long id);

    List<TagTopic> findLinkedTopics(long id);
    List<TagItem> findLinkedItems(long id);

    List<TagTopic> findItemLinkedTopics(long id);

    TagTopic createTopic(TagTopic topic);
    TagTopic updateTopic(TagTopic topic);

    TagItem createItem(TagItem item, String... topics);
    TagItem updateItem(TagItem item);

    List<TagItem> search(List<TagTopic> topics, int depth);

    // TODO remove item from topic -> update links
    // TODO remove item
    // TODO remove topic

}
