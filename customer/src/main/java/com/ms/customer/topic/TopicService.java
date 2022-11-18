package com.ms.customer.topic;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TopicService(TopicRepository topicRepository, Logger logger) {
    public List<Topic> findAll() {
        return this.topicRepository.findAll();
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
        validateNameIsTaken(topic.getName());
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
            logger.warn("Name '{}' is already taken", name);
            throw new BadRequest(String.format("Topic with name %s already exists", name));
        }
    }

    private void validateNameIsTaken(String name) {
        if (this.topicRepository.existsByName(name)) {
            logger.warn("Topic with name '{}' already exists.", name);
            throw new BadRequest(String.format("Topic with name %s already exists.", name));
        }
    }

    private void existsById(String topicId) {
        if (!this.topicRepository.existsById(topicId)) {
            logger.warn("Topic with id '{}' does not exsit.", topicId);
            throw new BadRequest(String.format("Topic with id %s does not exists", topicId));
        }
    }
}
