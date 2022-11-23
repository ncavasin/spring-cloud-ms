package com.ms.customer.seat;

import com.ms.customer.seat.entity.dto.SeatConverter;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.service.SeatService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/seat")
public record SeatController(SeatService seatService) {

    @GetMapping("/all")
    public Set<SeatDto> findAll() {
        return SeatConverter.convert(this.seatService.findAll());
    }

    @GetMapping("/available")
    public Set<SeatDto> findAvailable() {
        return SeatConverter.convert(this.seatService().findAvailable());
    }

    @GetMapping("/reserved")
    public Set<SeatDto> findReserved() {
        return SeatConverter.convert(this.seatService().findReserved());
    }

    @GetMapping("/{seatId}")
    public SeatDto findById(@PathVariable("seatId") String id) {
        return SeatConverter.convert(this.seatService.findById(id));
    }

    @PostMapping
    public SeatDto add(@Validated @RequestBody SeatDto seatDto) {
        return SeatConverter.convert(this.seatService.add(seatDto));
    }

    @PatchMapping("/{seatId}")
    public SeatDto update(@PathVariable("seatId") String id, @Validated @RequestBody SeatDto seatDto) {
        return SeatConverter.convert(this.seatService.update(id, seatDto));
    }

    @DeleteMapping("/{seatId}")
    public void delete(@PathVariable("seatId") String id) {
        this.seatService.delete(id);
    }
}
