package com.ms.customer.room;

import com.ms.customer.shared.converters.RoomConverter;
import com.ms.customer.room.entity.dto.RoomDto;
import com.ms.customer.room.service.RoomService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/room")
public record RoomController(RoomService roomService) {

    @GetMapping("/all")
    public Set<RoomDto> getAll() {
        return RoomConverter.convert(new HashSet<>(this.roomService.findAll()));
    }

    @GetMapping("/{roomId}")
    public RoomDto getById(@PathVariable("roomId") String roomId) {
        return RoomConverter.convert(this.roomService.findById(roomId));
    }

    @PostMapping()
    public RoomDto add(@Validated @RequestBody RoomDto roomDto) {
        return RoomConverter.convert(this.roomService.add(roomDto));
    }

    @PatchMapping("/{roomId}")
    public RoomDto update(@PathVariable("roomId") String roomId, @Validated @RequestBody RoomDto roomDto) {
        return RoomConverter.convert(this.roomService.update(roomId, roomDto));
    }

    @DeleteMapping("/{roomId}")
    public void delete(@PathVariable("roomId") String roomId) {
        this.roomService.delete(roomId);
    }
}
