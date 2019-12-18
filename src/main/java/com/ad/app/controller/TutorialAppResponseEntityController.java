package com.ad.app.controller;

import com.ad.app.model.Topic;
import com.ad.app.service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController /*All controller classes must be in the same package or extension of the same package where main method is*/
@RequestMapping("/response-entity-basics")
public class TutorialAppResponseEntityController {

    private final TopicService topicService;

    public TutorialAppResponseEntityController(TopicService topicService) { //TopicService will be Autowired since its a stereotype annotated class
        this.topicService = topicService;
    }

    @GetMapping("/greetings")
    public ResponseEntity<String> greet() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World!!");
    }

    @GetMapping("/topics")
    public ResponseEntity<List<Topic>> retrieveAllTopics() {
        return ResponseEntity.ok().body(topicService.getAllTopics());
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> retrieveTopicIdFromPathParam(@PathVariable String id) {
        return ResponseEntity.ok(topicService.getTopic(id));
    }

    @PostMapping("/topics")
    public ResponseEntity<Void> addTopic(@RequestBody @Valid Topic topic) {
        topicService.createTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/topics")
    public ResponseEntity<Void> updateTopic(@RequestParam(value="id") String id, @RequestBody Topic topic) {
        topicService.updateTopic(id, topic);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/topics")
    public ResponseEntity<Void> deleteTopic(@RequestHeader(value="topic-id") String id) {
        topicService.deleteTopic(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
