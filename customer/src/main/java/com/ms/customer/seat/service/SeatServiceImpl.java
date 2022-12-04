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
    public Seat findByNaturalId(SeatNaturalIdDto seatNaturalIdDto) {
        return this.seatRepository.findBySeatNaturalId(SeatNaturalId.builder()
                        .seatRow(seatNaturalIdDto.seatRow())
                        .seatColumn(seatNaturalIdDto.seatColumn())
                        .build())
                .orElseThrow(() -> {
                    logger.warn("Seat {}{} does not exist.", seatNaturalIdDto.seatRow(), seatNaturalIdDto.seatColumn());
                    throw new NotFound(String.format("Seat with id %s%d does not exist.",
                            seatNaturalIdDto.seatRow(), seatNaturalIdDto.seatColumn()));
                });
    }

    @Override
    public List<Seat> findAllByNaturalId(List<SeatNaturalIdDto> seatNaturalIdDtoList) {
        return this.seatRepository.findAllBySeatNaturalId(seatNaturalIdDtoList
                .stream()
                .map(s -> SeatNaturalId.builder()
                        .seatRow(s.seatRow())
                        .seatColumn(s.seatColumn())
                        .build())
                .collect(Collectors.toList()));
    }


    @Override
    public Seat add(SeatDto seatDto) {
        final Room room = this.roomService.findById(seatDto.roomId());
        checkNaturalIdIsNotTaken(seatDto);
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

    private void checkNaturalIdIsNotTaken(SeatDto seatDto) {
        if (this.seatRepository.existsBySeatNaturalId(seatDto.seatNaturalId())) {
            logger.warn("Could not create Seat {}{}. It already exists.", seatDto.seatNaturalId().getSeatRow(), seatDto.seatNaturalId().getSeatColumn());
            throw new BadRequest(String.format("Seat %s%s already exists.", seatDto.seatNaturalId().getSeatRow(), seatDto.seatNaturalId().getSeatColumn()));
        }
    }
}
