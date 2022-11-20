package com.ms.customer.movie;

import com.ms.customer.movie.dto.MovieDto;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import com.ms.customer.topic.Topic;
import com.ms.customer.topic.TopicServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public record MovieService(MovieRepository movieRepository, Logger logger, TopicServiceImpl topicServiceImpl) {

    public Set<Movie> getAll() {
        return new HashSet<>(this.movieRepository.findAll());
    }

    public Movie getById(String movieId) {
        return this.movieRepository.findById(movieId)
                .orElseThrow(() -> {
                    logger.warn("Movie with id '{}' does not exist.", movieId);
                    throw new NotFound(String.format("Movie with id %s not found.", movieId));
                });
    }

    public Movie addMovie(MovieDto movieDto) {
        return this.movieRepository.save(Movie.builder()
                .title(movieDto.title())
                .synopsis(movieDto.synopsis())
                .rating(movieDto.rating())
                .duration(movieDto.duration())
                .classification(movieDto.classification())
                .topics(fetchTopics(movieDto.topicIds()))
                .build());
    }


    public Movie updateMovie(String movieId, MovieDto movieDto) {
        Movie found = this.getById(movieId);
        found.setTitle(movieDto.title());
        found.setRating(movieDto.rating());
        found.setSynopsis(movieDto.synopsis());
        found.setDuration(movieDto.duration());
        found.setClassification(movieDto.classification());
        found.setTopics(fetchTopics(movieDto.topicIds()));
        logger.info("Movie with id '{}' updated.", movieId);
        return this.movieRepository.save(found);
    }

    public void deleteMovie(String movieId) {
        if (!this.movieRepository.existsById(movieId)) {
            logger.warn("Could not delete Movie with id '{}'. It does not exist.", movieId);
            throw new BadRequest(String.format("Movie with id %s does not exist.", movieId));
        }
        this.movieRepository.deleteById(movieId);
    }

    private Set<Topic> fetchTopics(Set<String> topicIds) {
        try {
            return topicIds
                    .stream()
                    .map(this.topicServiceImpl::findById)
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            log.error("A topic from this list {} was not found.", topicIds);
            throw new NotFound(String.format("A Topic from this list %s was not found", topicIds));
        }
    }
}
