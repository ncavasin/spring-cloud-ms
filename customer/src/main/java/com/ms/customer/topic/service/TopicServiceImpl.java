package com.ms.customer.topic.service;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import com.ms.customer.topic.entity.Topic;
import com.ms.customer.topic.entity.dto.TopicDto;
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

    public Topic add(TopicDto topicDto) {
        validateNameIsNotTaken(topicDto.name());
        logger.info("Topic with name '{}' and movie ids '{}' created.", topicDto.name(), topicDto.movieIds());
        return this.topicRepository.save(Topic.builder()
                .name(topicDto.name().toUpperCase())
                .movies(new HashSet<>())
                .build());
    }

    public Topic update(String id, TopicDto topicDto) {
        existsById(id);
        Topic found = this.findById(id);
        if (!found.getName().equals(topicDto.name()))
            validateNameIsNotTaken(topicDto.name());
        found.setName(topicDto.name().toUpperCase());
        logger.info("Topic with id '{}' updated.", topicDto.id());
        return this.topicRepository.save(found);
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
            logger.warn("Could not delete Topic with id '{}'. It does not exist.", topicId);
            throw new NotFound(String.format("Topic with id %s does not exist.", topicId));
        }
    }
}
