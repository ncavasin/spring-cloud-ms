package com.ms.customer.seat;

import com.ms.customer.seat.entity.dto.SeatConverter;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.service.SeatService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/seat")
public record SeatController(SeatService seatService) {

    @GetMapping("/all")
    public List<SeatDto> findAll() {
        return SeatConverter.convert(this.seatService.findAll());
    }

    @GetMapping("/available")
    public List<SeatDto> findAvailable() {
        return SeatConverter.convert(this.seatService().findNotSelected());
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

    @PatchMapping("/select")
    public List<SeatDto> select(@Validated @RequestBody Set<SeatDto> seatDtos) {
        return SeatConverter.convert(this.seatService.select(seatDtos));
    }

    @PatchMapping("/confirm")
    public List<SeatDto> confirm(@Validated @RequestBody Set<SeatDto> seatDtos) {
        return SeatConverter.convert(this.seatService.confirm(seatDtos));
    }

    @DeleteMapping("/{seatId}")
    public void delete(@PathVariable("seatId") String id) {
        this.seatService.delete(id);
    }
}
