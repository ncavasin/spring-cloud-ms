package com.ms.customer.room;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public record RoomService(RoomRepository roomRepository, Logger logger) {
    public List<Room> findAll() {
        return this.roomRepository.findAll();
    }

    public Room getById(String roomId) {
        return this.roomRepository.findById(roomId)
                .orElseThrow(() -> {
                    logger.warn("Room with id '{}' not found", roomId);
                    throw new NotFound(String.format("Room with id %s not found", roomId));
                });
    }

    public Room add(Room room) {
        logger.info("Room with name '{}' created.", room.getName());
        return this.roomRepository.save(room);
    }

    public Room update(String roomId, Room room) {
        validateRoomExistsById(roomId);
        room.setId(roomId);
        logger.info("Room with id '{}' updated.", roomId);
        return this.roomRepository.save(room);
    }

    public void delete(String roomId) {
        validateRoomExistsById(roomId);
        logger.info("Room with id {} deleted.", roomId);
        this.roomRepository.deleteById(roomId);
    }

    private void validateRoomExistsById(String roomId) {
        if (!this.roomRepository.existsById(roomId)) {
            logger.warn("Room with id '{}' does not exist.", roomId);
            throw new BadRequest(String.format("Room with id %s does not exist", roomId));
        }
    }
}
