package com.ms.customer.movie.dto;

import com.ms.customer.movie.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class MovieConverter {

    public static Movie convert(MovieDto movieDto) {
        return Movie.builder()
                .title(movieDto.title())
                .rating(movieDto.rating())
                .synopsis(movieDto.synopsis())
                .duration(movieDto.duration())
                .classification(movieDto.classification())
                .topics(movieDto.topics())
                .build();
    }

    public static MovieDto convert(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .synopsis(movie.getSynopsis())
                .duration(movie.getDuration())
                .classification(movie.getClassification())
                .rating(movie.getRating())
                .topics(movie.getTopics())
                .build();
    }

    public static List<MovieDto> convert(List<Movie> movies) {
        return movies.stream()
                .map(MovieConverter::convert)
                .collect(Collectors.toList());
    }
}
