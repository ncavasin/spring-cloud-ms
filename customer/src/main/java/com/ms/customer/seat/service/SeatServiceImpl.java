package com.ms.customer.seat.service;

import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.repository.SeatRepository;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public record SeatServiceImpl(Logger logger, SeatRepository seatRepository) implements SeatService {
    @Override
    public Set<Seat> findAll() {
        return new HashSet<>(this.seatRepository.findAll());
    }

    @Override
    public Set<Seat> findAvailable() {
        return new HashSet<>(this.seatRepository.findAvailable());
    }

    @Override
    public Set<Seat> findReserved() {
        return new HashSet<>(this.seatRepository.findReserved());
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
