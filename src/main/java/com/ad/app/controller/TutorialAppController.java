package com.ad.app.controller;

import com.ad.app.model.Topic;
import com.ad.app.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController /*All controller classes must be in the same package or extension of the same package where main method is*/
@RequestMapping("/spring-boot-basics")
public class TutorialAppController {

    @Autowired
    private TopicService topicService;

    /**
     * Dependency Injection is configured either with @Autowired annotation above or Constructor below.
     * Constructor is usually used for final fields since @Autowired doesn't work for final fields.
     */

    /*private final TopicService topicService;

    public TutorialAppController(TopicService topicService) { //TopicService will be Autowired since its a stereotype annotated class
        this.topicService = topicService;
    }*/

    @GetMapping("/greetings")
    public String greet() {
        return "Hello World!!";
    }

    @GetMapping("/topics")
    public List<Topic> retrieveAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping("/topics/{id}")
    public Topic retrieveTopicIdFromPathParam(@PathVariable String id) {
        return topicService.getTopic(id);
    }

    @PostMapping("/topics")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTopic(@RequestBody @Valid Topic topic) {
        topicService.createTopic(topic);
    }

    @PutMapping("/topics") //RESTful way to define the resource path would be '/topics/{id}'
    public void updateTopic(@RequestParam(value="id") String id, @RequestBody Topic topic) {
        topicService.updateTopic(id, topic);
    }

    @DeleteMapping("/topics") //RESTful way to define the resource path would be '/topics/{id}'
    public void deleteTopic(@RequestHeader(value="topic-id") String id) {
        topicService.deleteTopic(id);
    }
}
