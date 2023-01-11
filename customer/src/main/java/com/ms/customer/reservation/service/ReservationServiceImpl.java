package com.ms.customer.reservation.service;

import com.ms.customer.customer.entity.Customer;
import com.ms.customer.customer.service.CustomerService;
import com.ms.customer.reservation.entity.Reservation;
import com.ms.customer.reservation.entity.dto.ReservationDto;
import com.ms.customer.reservation.repository.ReservationRepository;
import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.service.SeatService;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public record ReservationServiceImpl(Logger logger,
                                     ReservationRepository reservationRepository,
                                     SeatService seatService,
                                     CustomerService customerService) implements ReservationService {
    @Override
    public List<Reservation> findAll() {
        return this.reservationRepository.findAll();
    }

    @Override
    public Reservation findById(String id) {
        return this.reservationRepository.findById(id).orElseThrow(() -> {
            logger.warn("Reservation with id {} not found. It does not exist", id);
            throw new NotFound(String.format("Reservation with id %s does not exist", id));
        });
    }

    @Override
    public Reservation add(ReservationDto reservationDto) {
        final Set<Seat> seats = new HashSet<>(this.seatService.findAllById(reservationDto.seatIds()));
        final Customer customer = this.customerService.findById(reservationDto.customerId());
        return this.reservationRepository.save(Reservation.builder()
                .seats(seats)
                .customer(customer)
                .build());
    }

    @Override
    public void delete(String id) {
        if (!this.reservationRepository.existsById(id)) {
            logger.warn("Could not delete Reservation with id {}. It does not exist", id);
            throw new BadRequest(String.format("Reservation with id %s does not exist", id));
        }
        this.reservationRepository.deleteById(id);
    }
}
