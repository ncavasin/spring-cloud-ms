package com.ms.customer.movie;

import com.ms.customer.shared.converters.MovieConverter;
import com.ms.customer.movie.dto.MovieDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/movie")
public record MovieController(MovieService movieService) {

    @GetMapping("/all")
    public Set<MovieDto> getAll() {
        return MovieConverter.convert(movieService.getAll());
    }

    @GetMapping("/{movieId}")
    public MovieDto getById(@PathVariable("movieId") String movieId) {
        return MovieConverter.convert(this.movieService.getById(movieId));
    }

    @PostMapping
    public MovieDto addMovie(@Validated @RequestBody MovieDto movieDto) {
        return MovieConverter.convert(movieService.addMovie(movieDto));
    }

    @PatchMapping("/{movieId}")
    public MovieDto updateMovie(@PathVariable("movieId") String movieId, @Validated @RequestBody MovieDto movieDto) {
        return MovieConverter.convert(movieService.updateMovie(movieId, movieDto));
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable("movieId") String movieId) {
        this.movieService.deleteMovie(movieId);
    }
}
