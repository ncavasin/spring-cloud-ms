package com.ms.customer.movie.entity.dto;

import com.ms.customer.movie.entity.Movie;
import com.ms.customer.shared.entities.AbstractEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieConverter {

    public static MovieDto convert(Movie movie) {
        Set<String> topics = movie.getTopics() == null ? null : movie.getTopics()
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toSet());
        Set<String> shows = movie.getShows() == null ? null : movie.getShows()
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toSet());
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .synopsis(movie.getSynopsis())
                .duration(movie.getDuration())
                .classification(movie.getClassification())
                .rating(movie.getRating())
                .topicIds(topics)
                .showIds(shows)
                .build();
    }

    public static Set<MovieDto> convert(Set<Movie> movies) {
        return movies.stream()
                .map(MovieConverter::convert)
                .collect(Collectors.toSet());
    }

    public static Set<String> collectMovieIds(Set<Movie> movies) {
        return movies
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toSet());
    }
}
