package com.ms.customer.topic.service;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import com.ms.customer.topic.entity.Topic;
import com.ms.customer.topic.repository.TopicRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public record TopicServiceImpl(TopicRepository topicRepository, Logger logger) implements TopicService {
    public Set<Topic> findAll() {
        return new HashSet<>(this.topicRepository.findAll());
    }

    public Topic findById(String topicId) {
        return this.topicRepository.findById(topicId)
                .orElseThrow(() -> {
                    logger.warn("Topic with id '{}' not found.", topicId);
                    throw new NotFound(String.format("Topic with id %s not found.", topicId));
                });
    }

    public Topic findByName(String name) {
        return this.topicRepository.findByName(name)
                .orElseThrow(() -> {
                    logger.warn("Topic with name '{}' not found.", name);
                    throw new NotFound(String.format("Topic with id %s not found.", name));
                });
    }

    public Topic add(Topic topic) {
        validateNameIsNotTaken(topic.getName());
        logger.info("Topic with name '{}' created.", topic.getName());
        return this.topicRepository.save(topic);
    }

    public Topic update(String topicId, Topic topic) {
        existsById(topicId);
        validateNameIsNotTaken(topic.getName());
        topic.setId(topicId);
        logger.info("Topic with id '{}' updated.", topic.getId());
        return this.topicRepository.save(topic);
    }

    public void delete(String topicId) {
        existsById(topicId);
        logger.info("Topic with id '{}' deleted.", topicId);
        this.topicRepository.deleteById(topicId);
    }

    private void validateNameIsNotTaken(String name) {
        if (this.topicRepository.existsByName(name)) {
            logger.warn("Topic name '{}' is already taken.", name);
            throw new BadRequest(String.format("Topic name %s already exists.", name));
        }
    }

    private void existsById(String topicId) {
        if (!this.topicRepository.existsById(topicId)) {
            logger.warn("Topic with id '{}' does not exsit.", topicId);
            throw new BadRequest(String.format("Topic with id %s does not exists", topicId));
        }
    }
}
