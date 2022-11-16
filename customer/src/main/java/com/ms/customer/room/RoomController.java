package com.ms.customer.room;

import com.ms.customer.room.dto.RoomConverter;
import com.ms.customer.room.dto.RoomDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public record RoomController(RoomService roomService) {

    @GetMapping("/all")
    public List<RoomDto> getAll() {
        return RoomConverter.convert(this.roomService.findAll());
    }

    @GetMapping("/{roomId}")
    public RoomDto getById(@PathVariable("roomId") String roomId) {
        return RoomConverter.convert(this.roomService.getById(roomId));
    }

    @PostMapping()
    public RoomDto add(@Validated @RequestBody RoomDto roomDto) {
        return RoomConverter.convert(this.roomService.add(RoomConverter.convert(roomDto)));
    }

    @PatchMapping("/{roomId}")
    public RoomDto update(@PathVariable("roomId") String roomId, @Validated @RequestBody RoomDto roomDto) {
        return RoomConverter.convert(this.roomService.update(roomId, RoomConverter.convert(roomDto)));
    }

    @DeleteMapping("/{roomId}")
    public void delete(@PathVariable("roomId") String roomId){
        this.roomService.delete(roomId);
    }
}
