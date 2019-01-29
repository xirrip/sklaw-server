package ch.skunky.skunklaw.controller;

import ch.skunky.skunklaw.model.tags.TagItem;
import ch.skunky.skunklaw.model.tags.TagTopic;
import ch.skunky.skunklaw.service.TaggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags/*")
public class TaggingController {

    @Autowired
    private TaggingService taggingService;

    @GetMapping("/topics")
    public ResponseEntity<List<TagTopic>> getTopics(){
        return ResponseEntity.ok(taggingService.findAllTopics());
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<TagTopic> getTopic(@PathVariable long id){
        return ResponseEntity.of(taggingService.findTopic(id));
    }

    @GetMapping("/topics/{id}/links")
    public ResponseEntity<List<TagTopic>> getLinkedTopics(@PathVariable long id){
        return ResponseEntity.ok(taggingService.findLinkedTopics(id));
    }

    @GetMapping("/topics/{id}/items")
    public ResponseEntity<List<TagItem>> getLinkedItems(@PathVariable long id){
        return ResponseEntity.ok(taggingService.findLinkedItems(id));
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<TagTopic> updateTopic(@RequestBody TagTopic topic, @PathVariable long id) {
        if(topic.getId()!= id) throw new IllegalArgumentException("Topic id does not correspond to request path.");
        taggingService.updateTopic(topic);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/topics")
    public ResponseEntity<TagTopic> createTopic(@RequestBody TagTopic topic) {
        return ResponseEntity.ok(taggingService.createTopic(topic));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<TagItem> getItem(@PathVariable long id){
        return ResponseEntity.of(taggingService.findItem(id));
    }

    @GetMapping("/items/{id}/topics")
    public ResponseEntity<List<TagTopic>> getItemLinkedTopics(@PathVariable long id){
        return ResponseEntity.ok(taggingService.findItemLinkedTopics(id));
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<TagItem> updateItem(@RequestBody TagItem item, @PathVariable long id) {
        if(item.getId()!= id) throw new IllegalArgumentException("Item id does not correspond to request path.");
        taggingService.updateItem(item);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/items")
    public ResponseEntity<TagItem> createItem(@RequestBody TagItem item) {
        String[] topics = item.getTopics().split(",");
        TagItem savedItem = taggingService.createItem(item, topics);
        return ResponseEntity.ok(savedItem);
    }

    @GetMapping("/items")
    public ResponseEntity<List<TagItem>> searchItems(@RequestParam("universe") String universe, @RequestParam("topics") String topics, @RequestParam("depth") int depth){
        String[] topicNames = topics.split(",");
        List<TagTopic> tagTopics = new ArrayList<>(topicNames.length);
        for(String topicName : topicNames){
            Optional<TagTopic> topic = taggingService.findTopic(topicName.trim(), universe.trim());
            topic.ifPresent(tagTopics::add);
        }

        List<TagItem> items = taggingService.search(tagTopics, depth);
        return ResponseEntity.ok(items);
    }

}
