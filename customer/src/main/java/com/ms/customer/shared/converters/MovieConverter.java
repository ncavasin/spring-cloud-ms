package com.ms.customer.shared.converters;

import com.ms.customer.movie.Movie;
import com.ms.customer.movie.dto.MovieDto;
import com.ms.customer.shared.entities.AbstractEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class MovieConverter {

    public static MovieDto convert(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .synopsis(movie.getSynopsis())
                .duration(movie.getDuration())
                .classification(movie.getClassification())
                .rating(movie.getRating())
                .topicIds(movie.getTopics()
                        .stream()
                        .map(AbstractEntity::getId)
                        .collect(Collectors.toSet()))
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
