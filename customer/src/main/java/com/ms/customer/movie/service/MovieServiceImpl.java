package com.ms.customer.movie.service;

import com.ms.customer.movie.entity.Movie;
import com.ms.customer.movie.entity.dto.MovieDto;
import com.ms.customer.movie.repository.MovieRepository;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import com.ms.customer.topic.entity.Topic;
import com.ms.customer.topic.service.TopicServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public record MovieServiceImpl(MovieRepository movieRepository, Logger logger,
                               TopicServiceImpl topicServiceImpl) implements MovieService {

    public Set<Movie> findAll() {
        return new HashSet<>(this.movieRepository.findAll());
    }

    public Movie findById(String id) {
        return this.movieRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Movie with id '{}' does not exist.", id);
                    throw new NotFound(String.format("Movie with id %s not found.", id));
                });
    }

    public Movie add(MovieDto movieDto) {
        return this.movieRepository.save(Movie.builder()
                .title(movieDto.title())
                .synopsis(movieDto.synopsis())
                .rating(movieDto.rating())
                .duration(movieDto.duration())
                .classification(movieDto.classification())
                .topics(fetchTopics(movieDto.topicIds()))
                .build());
    }


    public Movie update(String id, MovieDto movieDto) {
        Movie found = this.findById(id);
        found.setTitle(movieDto.title());
        found.setRating(movieDto.rating());
        found.setSynopsis(movieDto.synopsis());
        found.setDuration(movieDto.duration());
        found.setClassification(movieDto.classification());
        found.setTopics(fetchTopics(movieDto.topicIds()));
        logger.info("Movie with id '{}' updated.", id);
        return this.movieRepository.save(found);
    }

    public void delete(String movieId) {
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
