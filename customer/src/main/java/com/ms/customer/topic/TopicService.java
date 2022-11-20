package com.ms.customer.topic;

import java.util.Set;

public interface TopicService {

    Set<Topic> findAll();

    Topic findById(String topicId);

    Topic findByName(String name);

    Topic add(Topic topic);

    Topic update(String topicId, Topic topic);

    void delete(String topicId);
}
