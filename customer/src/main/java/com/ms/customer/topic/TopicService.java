package com.ms.customer.topic;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TopicService(TopicRepository topicRepository) {
    public List<Topic> findAll() {
        return this.topicRepository.findAll();
    }

    public Topic findById(String topicId) {
        return this.topicRepository.findById(topicId)
                .orElseThrow(() -> new NotFound(String.format("Topic with id %s not found.", topicId)));
    }

    public Topic add(Topic topic) {
        return this.topicRepository.save(topic);
    }

    public Topic update(String topicId, Topic topic) {
        existsById(topicId);
        topic.setId(topicId);
        return this.topicRepository.save(topic);
    }

    private void existsById(String topicId) {
        if (!this.topicRepository.existsById(topicId)) {
            throw new BadRequest(String.format("Topic with id %s does not exists", topicId));
        }
    }

    public void delete(String topicId) {
        existsById(topicId);
        this.topicRepository.deleteById(topicId);
    }
}
