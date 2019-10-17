package com.ad.app.service;

import com.ad.app.model.Topic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class TopicService {

    private List<Topic> topicList = new ArrayList<>(
            Arrays.asList(
                    new Topic("1", "Spring", "Learn about Spring Framework"),
                    new Topic("2", "SpringBoot", "Learn about Spring Boot")
            )
    );


    public List<Topic> getAllTopics() {
        log.debug("Retrieving all topics");
        return topicList;
    }


    public Topic getTopic(String id) {
        log.debug("Retrieving topic# {}", id);
        return topicList.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }


    public void createTopic(Topic topic) {
        log.info("Creating new topic");
        topicList.add(topic);
    }


    public void updateTopic(String id, Topic topic) {
        Topic topicToUpdate = null;

        for( Topic t : topicList) {
            if (id.equals(t.getId())) {
                topicToUpdate = t;
                break;
            }
        }

        if( topicToUpdate != null ) {
            if( topic.getName() != null && !topic.getName().isEmpty() )
                topicToUpdate.setName(topic.getName());

            if( topic.getDescription() != null && !topic.getDescription().isEmpty() )
                topicToUpdate.setDescription(topic.getDescription());
        }
    }


    public void deleteTopic(String id) {
        log.warn("Deleting topic# {}", id);
        topicList.removeIf(t -> t.getId().equals(id));
    }
}
