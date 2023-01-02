package com.ms.customer.show.service;

import com.ms.customer.movie.entity.Movie;
import com.ms.customer.movie.service.MovieService;
import com.ms.customer.room.entity.Room;
import com.ms.customer.room.service.RoomService;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import com.ms.customer.show.entity.Show;
import com.ms.customer.show.entity.dto.ShowDto;
import com.ms.customer.show.repository.ShowRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
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
    public Set<Show> findByDateAndBeginTimeAndEndTime(Date date, LocalTime beginTime, LocalTime endTime) {
        return new HashSet<>(this.showRepository.findByDateAndBeginTimeAndEndTime(date, beginTime, endTime));
    }

    @Override
    public Show add(ShowDto showDto) {
        checkShowExistence(showDto);
        final Movie movie = this.movieService.findById(showDto.movieId());
        final Room room = this.roomService.findById(showDto.roomId());
        logger.info("Show for movie '{}' in room '{}' with begin time '{}' created.", movie.getTitle(), room.getName(), showDto.beginTime());
        return this.showRepository.save(Show.builder()
                .date(showDto.date())
                .beginTime(showDto.beginTime())
                .endTime(showDto.endTime())
                .movie(movie)
                .room(room)
                .build());
    }

    @Override
    public Show update(String id, ShowDto showDto) {
        checkShowExistence(showDto);
        Show found = this.findById(id);
        final Movie movie = this.movieService.findById(showDto.movieId());
        final Room room = this.roomService.findById(showDto.roomId());
        found.setDate(showDto.date());
        found.setBeginTime(showDto.beginTime());
        found.setEndTime(showDto.endTime());
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

    private void checkShowExistence(ShowDto showDto) {
        if (this.showRepository.existsByRoomIdAndDateAndBeginTimeAndEndTime(showDto.roomId(), showDto.date(),
                showDto.beginTime(), showDto.endTime())) {
            log.warn("A show in room {} with date {}, begin time '{}' and end time '{}' already exists!",
                    showDto.roomId(), showDto.date(), showDto.beginTime(), showDto.endTime());
            throw new BadRequest(String.format("A show in room %s with date '%s', begin time '%s' and end time '%s' " +
                    "already exists!", showDto.roomId(), showDto.date(), showDto.beginTime(), showDto.endTime()));
        }
    }
}
