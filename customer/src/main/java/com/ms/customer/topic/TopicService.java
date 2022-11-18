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

    public Topic add(Topic topic) {
        logger.info("Topic with name '{}' created.", topic.getName());
        return this.topicRepository.save(topic);
    }

    public Topic update(String topicId, Topic topic) {
        existsById(topicId);
        topic.setId(topicId);
        logger.info("Topic with id '{}' updated.", topic.getId());
        return this.topicRepository.save(topic);
    }

    public void delete(String topicId) {
        existsById(topicId);
        logger.info("Topic with id '{}' deleted.", topicId);
        this.topicRepository.deleteById(topicId);
    }

    private void existsById(String topicId) {
        if (!this.topicRepository.existsById(topicId)) {
            logger.warn("Topic with id '{}' does not exsit.", topicId);
            throw new BadRequest(String.format("Topic with id %s does not exists", topicId));
        }
    }
}
