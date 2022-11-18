package com.ms.customer.topic.dto;

import com.ms.customer.topic.Topic;

import java.util.List;
import java.util.stream.Collectors;

public class TopicConverter {

    public static TopicDto convert(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .name(topic.getName())
                .build();
    }

    public static List<TopicDto> convert(List<Topic> topics) {
        return topics.stream()
                .map(TopicConverter::convert)
                .collect(Collectors.toList());
    }

    public static Topic convert(TopicDto topicDto) {
        return Topic.builder()
                .name(topicDto.name())
                .build();
    }
}
