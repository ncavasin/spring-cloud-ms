package com.ms.customer.seat;

import com.ms.customer.seat.entity.dto.SeatConverter;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.entity.dto.SeatNaturalIdDto;
import com.ms.customer.seat.service.SeatService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat")
public record SeatController(SeatService seatService) {

    @GetMapping("/all")
    public List<SeatDto> findAll() {
        return SeatConverter.convert(this.seatService.findAll());
    }

    @GetMapping("/all-by-natural-id")
    public List<SeatDto> findAllByNaturalId(@Validated @RequestBody List<SeatNaturalIdDto> naturalIdDtoList) {
        return SeatConverter.convert(this.seatService.findAllByNaturalId(naturalIdDtoList));
    }

    @GetMapping("/{seatId}")
    public SeatDto findById(@PathVariable("seatId") String id) {
        return SeatConverter.convert(this.seatService.findById(id));
    }

    @PostMapping("/by-natural-id")
    public SeatDto findByNaturalId(@Validated @RequestBody SeatNaturalIdDto seatNaturalIdDto) {
        return SeatConverter.convert(this.seatService.findByNaturalId(SeatConverter.convert(seatNaturalIdDto)));
    }

    @PostMapping
    public SeatDto add(@Validated @RequestBody SeatDto seatDto) {
        return SeatConverter.convert(this.seatService.add(seatDto));
    }

    @DeleteMapping("/{seatId}")
    public void delete(@PathVariable("seatId") String id) {
        this.seatService.delete(id);
    }
}
