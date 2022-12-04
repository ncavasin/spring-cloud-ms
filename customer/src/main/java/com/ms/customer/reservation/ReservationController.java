package com.ms.customer.reservation;

import com.ms.customer.reservation.entity.dto.ReservationConverter;
import com.ms.customer.reservation.entity.dto.ReservationDto;
import com.ms.customer.reservation.service.ReservationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public record ReservationController(ReservationService reservationService) {

    @GetMapping("/all")
    public List<ReservationDto> findAll() {
        return ReservationConverter.convert(this.reservationService.findAll());
    }

    @GetMapping("/{reservationId}")
    public ReservationDto findById(@PathVariable("reservationId") String id) {
        return ReservationConverter.convert(this.reservationService.findById(id));
    }

    @PostMapping
    public ReservationDto add(@Validated @RequestBody ReservationDto reservationDto) {
        return ReservationConverter.convert(this.reservationService.add(reservationDto));
    }

    @DeleteMapping("/{reservationId}")
    public void delete(@PathVariable("reservationId") String id) {
        this.reservationService.delete(id);
    }
}
