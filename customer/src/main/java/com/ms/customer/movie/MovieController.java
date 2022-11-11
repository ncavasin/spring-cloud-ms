package com.ms.customer.movie;

import com.ms.customer.movie.dto.MovieConverter;
import com.ms.customer.movie.dto.MovieCreationDto;
import com.ms.customer.movie.dto.MovieDto;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public record MovieController(MovieService movieService, ModelMapper modelMapper) {

    @GetMapping
    public List<MovieDto> getAll() {
        return MovieConverter.convert(movieService.getAll());
    }

    @GetMapping("/{movieId}")
    public MovieDto getById(@PathVariable("movieId") String movieId) {
        return MovieConverter.convert(this.movieService.getById(movieId));
    }

    @PostMapping
    public MovieDto addMovie(@Validated @RequestBody MovieCreationDto movieCreationDto) {
        return MovieConverter.convert(movieService.addMovie(MovieConverter.convert(movieCreationDto)));
    }

    @PatchMapping("/{movieId}")
    public MovieDto updateMovie(@PathVariable("movieId") String movieId, @Validated @RequestBody MovieDto movieDto) {
        return MovieConverter.convert(movieService.updateMovie(movieId, MovieConverter.convert(movieDto)));
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable("movieId") String movieId) {
        this.movieService.deleteMovie(movieId);
    }
}