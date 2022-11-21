package com.ms.customer.room.service;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.branch.service.BranchService;
import com.ms.customer.room.entity.Room;
import com.ms.customer.room.entity.dto.RoomDto;
import com.ms.customer.room.repository.RoomRepository;
import com.ms.customer.screenFormat.entity.ScreenFormat;
import com.ms.customer.screenFormat.service.ScreenFormatService;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public record RoomServiceImpl(RoomRepository roomRepository, Logger logger,
                              BranchService branchService,
                              ScreenFormatService screenFormatService) implements RoomService {
    public Set<Room> findAll() {
        return new HashSet<>(this.roomRepository.findAll());
    }

    @Override
    public Set<Room> findAllById(Set<String> ids) {
        return new HashSet<>(this.roomRepository.findAllById(ids));
    }

    public Room findById(String roomId) {
        return this.roomRepository.findById(roomId)
                .orElseThrow(() -> {
                    logger.warn("Room with id '{}' not found", roomId);
                    throw new NotFound(String.format("Room with id %s not found", roomId));
                });
    }

    public Room add(RoomDto roomDto) {
        logger.info("Room with name '{}' created.", roomDto.name());
        return this.roomRepository.save(Room.builder()
                .name(roomDto.name())
                .screenFormat(fetchScreenFormat(roomDto.screenFormatId()))
                .branch(fetchBranch(roomDto.branchId()))
                .build());
    }

    public Room update(String id, RoomDto roomDto) {
        validateRoomExistsById(id);
        Room found = this.findById(id);
        found.setName(roomDto.name());
        found.setScreenFormat(fetchScreenFormat(roomDto.screenFormatId()));
        found.setBranch(fetchBranch(roomDto.branchId()));
        logger.info("Room with id '{}' updated.", id);
        return this.roomRepository.save(found);
    }

    public void delete(String id) {
        validateRoomExistsById(id);
        logger.info("Room with id {} deleted.", id);
        this.roomRepository.deleteById(id);
    }

    private void validateRoomExistsById(String id) {
        if (!this.roomRepository.existsById(id)) {
            logger.warn("Room with id '{}' does not exist.", id);
            throw new BadRequest(String.format("Room with id %s does not exist", id));
        }
    }

    private ScreenFormat fetchScreenFormat(String id) {
        return this.screenFormatService.findById(id);
    }

    private Branch fetchBranch(String id) {
        return this.branchService.findById(id);
    }
}
