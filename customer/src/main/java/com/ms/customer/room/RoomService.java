package com.ms.customer.room;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record RoomService(RoomRepository roomRepository) {
    public List<Room> findAll() {
        return this.roomRepository.findAll();
    }

    public Room getById(String roomId) {
        return this.roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFound(String.format("Room with id %s not found", roomId)));
    }

    public Room add(Room room) {
        return this.roomRepository.save(room);
    }

    public Room update(String roomId, Room room) {
        validateRoomExistsById(roomId);
        room.setId(roomId);
        return this.roomRepository.save(room);
    }

    public void delete(String roomId) {
        validateRoomExistsById(roomId);
        this.roomRepository.deleteById(roomId);
    }

    private void validateRoomExistsById(String roomId) {
        if (!this.roomRepository.existsById(roomId)) {
            throw new BadRequest(String.format("Room with id %s does not exist", roomId));
        }
    }
}
