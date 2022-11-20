package com.ms.customer.movie;

import com.ms.customer.movie.entity.dto.MovieDto;
import com.ms.customer.movie.service.MovieService;
import com.ms.customer.movie.entity.dto.MovieConverter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/movie")
public record MovieController(MovieService movieService) {

    @GetMapping("/all")
    public Set<MovieDto> getAll() {
        return MovieConverter.convert(movieService.findAll());
    }

    @GetMapping("/{movieId}")
    public MovieDto getById(@PathVariable("movieId") String movieId) {
        return MovieConverter.convert(this.movieService.findById(movieId));
    }

    @PostMapping
    public MovieDto addMovie(@Validated @RequestBody MovieDto movieDto) {
        return MovieConverter.convert(movieService.add(movieDto));
    }

    @PatchMapping("/{movieId}")
    public MovieDto updateMovie(@PathVariable("movieId") String movieId, @Validated @RequestBody MovieDto movieDto) {
        return MovieConverter.convert(movieService.update(movieId, movieDto));
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable("movieId") String movieId) {
        this.movieService.delete(movieId);
    }
}
