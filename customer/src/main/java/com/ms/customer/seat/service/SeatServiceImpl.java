package com.ms.customer.seat.service;

import com.ms.customer.room.entity.Room;
import com.ms.customer.room.service.RoomService;
import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.SeatNaturalId;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.entity.dto.SeatNaturalIdDto;
import com.ms.customer.seat.repository.SeatRepository;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Seat> findAllById(List<String> ids) {
        return this.seatRepository.findAllById(ids);
    }

    @Override
    public Seat findById(String id) {
        return this.seatRepository.findById(id).orElseThrow(() -> {
            logger.warn("Seat with id {} does not exist.", id);
            throw new NotFound(String.format("Seat with id %s does not exist.", id));
        });
    }

    @Override
    public Seat findByNaturalId(SeatNaturalId seatNaturalId) {
        return this.seatRepository.findBySeatNaturalId(seatNaturalId)
                .orElseThrow(() -> {
                    logger.warn("Seat {}{} does not exist.", seatNaturalId.getSeatColumn(), seatNaturalId.getSeatColumn());
                    throw new NotFound(String.format("Seat with id %s%d does not exist.",
                            seatNaturalId.getSeatColumn(), seatNaturalId.getSeatColumn()));
                });
    }

    @Override
    public List<Seat> findAllByNaturalId(List<SeatNaturalIdDto> seatNaturalIdDtoList) {
        return this.seatRepository.findAllBySeatNaturalId(seatNaturalIdDtoList
                .stream()
                .map(s -> com.ms.customer.seat.entity.SeatNaturalId.builder()
                        .seatRow(s.seatRow())
                        .seatColumn(s.seatColumn())
                        .build())
                .collect(Collectors.toList()));
    }

    @Override
    public Seat add(SeatDto seatDto) {
        final Room room = this.roomService.findById(seatDto.roomId());
        checkNaturalIdIsUnique(seatDto);
        log.info("Seat {}{} created.", seatDto.seatNaturalId().getSeatRow(), seatDto.seatNaturalId().getSeatColumn());
        return this.seatRepository.save(Seat.builder()
                .seatNaturalId(seatDto.seatNaturalId())
                .room(room)
                .build());
    }

    @Override
    public void delete(String id) {
        if (!this.seatRepository.existsById(id)) {
            log.warn("Could not delete Seat with id {}. It does not exist", id);
            throw new BadRequest(String.format("Seat with id %s does not exist", id));
        }
        this.seatRepository.deleteById(id);
        logger.info("Seat with id {} deleted.", id);
    }

    private void checkNaturalIdIsUnique(SeatDto seatDto) {
        if (this.seatRepository.existsBySeatNaturalId(seatDto.seatNaturalId())) {
            logger.warn("Seat {}{} already exists for room id '{}'.", seatDto.seatNaturalId().getSeatRow(),
                    seatDto.seatNaturalId().getSeatColumn(), seatDto.roomId());
            throw new BadRequest(String.format("Seat %s%s already exists for room id '%s'.",
                    seatDto.seatNaturalId().getSeatRow(), seatDto.seatNaturalId().getSeatColumn(), seatDto.roomId()));
        }
    }
}
