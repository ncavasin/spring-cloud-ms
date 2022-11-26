package com.ms.customer.seat.service;

import com.ms.customer.room.entity.Room;
import com.ms.customer.room.service.RoomService;
import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.repository.SeatRepository;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public record SeatServiceImpl(Logger logger, SeatRepository seatRepository,
                              RoomService roomService) implements SeatService {
    @Override
    public List<Seat> findAll() {
        return this.seatRepository.findAll();
    }

    @Override
    public List<Seat> findNotSelected() {
        return this.seatRepository.findNotSelected();
    }

    @Override
    public List<Seat> findReserved() {
        return this.seatRepository.findConfirmed();
    }

    @Override
    public Seat findById(String id) {
        return this.seatRepository.findById(id).orElseThrow(() -> {
            logger.info("Seat with id {} does not exist.", id);
            throw new NotFound(String.format("Seat with id %s does not exist.", id));
        });
    }

    @Override
    public Seat add(SeatDto seatDto) {
        return this.seatRepository.save(Seat.builder().seatNaturalId(seatDto.seatNaturalId()).build());
    }

    @Override
    public Seat update(String id, SeatDto seatDto) {
        Seat found = this.findById(id);

        return this.seatRepository.save(found);
    }

    @Override
    public void delete(String id) {
        if (!this.seatRepository.existsById(id)) {
            log.warn("Could not delete Seat with id {}. It does not exist", id);
            throw new BadRequest(String.format("Seat with id %s does not exist", id));
        }
        this.seatRepository.deleteById(id);
    }
}
