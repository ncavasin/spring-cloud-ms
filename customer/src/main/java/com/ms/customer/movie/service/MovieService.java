package com.ms.customer.movie.service;

import com.ms.customer.movie.entity.Movie;
import com.ms.customer.movie.entity.dto.MovieDto;

import java.util.Set;

public interface MovieService {

    Set<Movie> findAll();

    Set<Movie> findAllById(Set<String> movieIds);

    Movie findById(String id);

    Movie add(MovieDto movieDto);

    Movie update(String id, MovieDto movieDto);

    void delete(String id);
}
