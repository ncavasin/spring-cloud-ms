package com.ms.customer.topic.service;

import com.ms.customer.topic.entity.Topic;
import com.ms.customer.topic.entity.dto.TopicDto;

import java.util.Set;

public interface TopicService {

    Set<Topic> findAll();

    Topic findById(String topicId);

    Topic findByName(String name);

    Topic add(TopicDto topicDto);

    Topic update(String id, TopicDto topicDto);

    void delete(String topicId);
}
