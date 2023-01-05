package com.ms.customer;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import com.ms.customer.topic.entity.Topic;
import com.ms.customer.topic.entity.dto.TopicDto;
import com.ms.customer.topic.service.TopicService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class TopicServiceTest {

    @Autowired
    private TopicService topicService;

    private Topic createdTopic;

    @Before
    @Transactional
    public void setUp() {
        this.createdTopic = this.topicService.add(TopicDto.builder()
                .name("NEW_TOPIC")
                .build());
    }

    @Test
    @Transactional
    public void createTopic() {
        Assert.assertEquals(this.createdTopic, this.topicService.findById(this.createdTopic.getId()));
    }

    @Test
    @Transactional
    public void findByName() {
        final String name = "REPEATABLE NAME";
        final Topic newTopic = this.topicService.add(TopicDto.builder()
                .name(name)
                .build());
        Assert.assertEquals(name, this.topicService.findByName(newTopic.getName()).getName());
    }

    @Test
    @Transactional
    public void createTopicWithInvalidMovie_throwsBadRequest() {
        Assert.assertThrows(BadRequest.class, () -> this.topicService.add(TopicDto.builder()
                .name("NEW_TOPIC")
                .build()));
    }

    @Test
    @Transactional
    public void updateTopicName() {
        final String new_name = "NEW_NAME";
        this.topicService.update(this.createdTopic.getId(), TopicDto.builder()
                .name(new_name)
                .build());
        Assert.assertEquals(new_name, this.topicService.findById(this.createdTopic.getId()).getName());
    }

    @Test
    @Transactional
    public void updateTopicNameWithTakenName_throwsBadRequest() {
        final String repeatableName = "REPEATABLE NAME";
        this.topicService.add(TopicDto.builder()
                .name(repeatableName)
                .build());
        Assert.assertThrows(BadRequest.class, () -> this.topicService.update(this.createdTopic.getId(),
                TopicDto.builder()
                        .name(repeatableName)
                        .build()));
    }

    @Test
    @Transactional
    public void deleteTopic() {
        this.topicService.delete(this.createdTopic.getId());
        Assert.assertThrows(NotFound.class, () -> this.topicService.findById(this.createdTopic.getId()));
    }

    @Test
    @Transactional
    public void deleteNonExistentTopic_throwsNotFound() {
        Assert.assertThrows(NotFound.class, () -> this.topicService.delete("NON_EXISTENT_ID"));
    }

    @Test
    @Transactional
    public void updateTopicMovies_keepsNull_moviesAreUpdatedByCascade() {
        this.topicService.update(this.createdTopic.getId(), TopicDto.builder()
                .name(this.createdTopic.getName())
                .movieIds(Set.of("ANY_MOVIE"))
                .build());
        Assert.assertTrue(this.topicService.findById(this.createdTopic.getId()).getMovies().isEmpty());
    }

}
