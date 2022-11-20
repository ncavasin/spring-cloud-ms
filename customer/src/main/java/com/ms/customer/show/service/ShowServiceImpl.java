package com.ms.customer.show.service;

import com.ms.customer.movie.entity.Movie;
import com.ms.customer.movie.service.MovieService;
import com.ms.customer.room.entity.Room;
import com.ms.customer.room.service.RoomService;
import com.ms.customer.shared.exceptions.NotFound;
import com.ms.customer.show.entity.dto.ShowDto;
import com.ms.customer.show.entity.Show;
import com.ms.customer.show.repository.ShowRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public record ShowServiceImpl(Logger logger, ShowRepository showRepository, MovieService movieService,
                              RoomService roomService) implements ShowService {
    @Override
    public Set<Show> findAll() {
        return new HashSet<>(this.showRepository.findAll());
    }

    @Override
    public Set<Show> findByDate(Date date) {
        return new HashSet<>(this.showRepository.findByDate(date));
    }

    @Override
    public Show findById(String id) {
        return this.showRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Show with id {} not found", id);
                    throw new NotFound(String.format("Show with id %s not found.", id));
                });
    }

    @Override
    public Show add(ShowDto showDto) {
        final Movie movie = this.movieService.findById(showDto.movieId());
        final Room room = this.roomService.findById(showDto.roomId());
        logger.info("Show for movie '{}' in room '{}' with date '{}' created.", movie.getTitle(), room.getName(), showDto.date());
        return this.showRepository.save(Show.builder()
                .date(showDto.date())
                .begin(showDto.begin())
                .end(showDto.end())
                .movie(movie)
                .room(room)
                .build());
    }

    @Override
    public Show update(String id, ShowDto showDto) {
        Show found = this.findById(id);
        final Movie movie = this.movieService.findById(showDto.movieId());
        final Room room = this.roomService.findById(showDto.roomId());
        found.setDate(showDto.date());
        found.setBegin(showDto.begin());
        found.setEnd(showDto.end());
        found.setMovie(movie);
        found.setRoom(room);
        logger.info("Show with id '{}' updated.", id);
        return this.showRepository.save(found);
    }

    @Override
    public void delete(String id) {
        if (!this.showRepository.existsById(id)) {
            logger.warn("Could not delete Show with id '{}'. It does not exist", id);
            throw new NotFound(String.format("Show with id %s does not exist", id));
        }
        this.showRepository.deleteById(id);
    }
}
