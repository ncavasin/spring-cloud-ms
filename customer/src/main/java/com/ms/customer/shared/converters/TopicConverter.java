package com.ms.customer.shared.converters;

import com.ms.customer.topic.entity.Topic;
import com.ms.customer.topic.entity.dto.TopicDto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TopicConverter {

    public static TopicDto convert(Topic topic) {
        return TopicDto.builder()
                .id(topic.getId())
                .name(topic.getName())
                .movieIds(MovieConverter.collectMovieIds(topic.getMovies()))
                .build();
    }

    public static Set<TopicDto> convert(Set<Topic> topics) {
        return topics.stream()
                .map(TopicConverter::convert)
                .collect(Collectors.toSet());
    }

    public static Topic convert(TopicDto topicDto) {
        return Topic.builder()
                .name(topicDto.name())
                .movies(new HashSet<>())
                .build();
    }


}
