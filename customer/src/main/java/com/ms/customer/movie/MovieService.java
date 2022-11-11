package com.ms.customer.movie;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public record MovieService(MovieRepository movieRepository) {

    public List<Movie> getAll() {
        return this.movieRepository.findAll();
    }

    public Movie getById(String movieId) {
        return this.movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFound(String.format("Movie with id %s not found.", movieId)));
    }

    public Movie addMovie(Movie movie) {
        return this.movieRepository.save(movie);
    }


    public Movie updateMovie(String movieId, Movie movieDto) {
        Movie movie = this.getById(movieId);
        movie.setTitle(movieDto.title);
        movie.setRating(movie.getRating());
        movie.setSynopsis(movieDto.synopsis);
        movie.setDuration(movie.duration);
        movie.setClassification(movieDto.getClassification());
        return this.movieRepository.save(movie);
    }

    public void deleteMovie(String movieId) {
        if (!this.movieRepository.existsById(movieId))
            throw new BadRequest(String.format("Movie with id %s does not exist.", movieId));
        this.movieRepository.deleteById(movieId);
    }
}
