package com.ms.customer.screenFormat.service;

import com.ms.customer.screenFormat.entity.ScreenFormat;
import com.ms.customer.screenFormat.entity.dto.ScreenFormatDto;
import com.ms.customer.screenFormat.repository.ScreenFormatRepository;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public record ScreenFormatServiceImpl(ScreenFormatRepository screenFormatRepository,
                                      Logger logger) implements ScreenFormatService {
    public Set<ScreenFormat> findAll() {
        return new HashSet<>(this.screenFormatRepository.findAll());
    }

    public ScreenFormat findById(String screenFormatId) {
        return this.screenFormatRepository.findById(screenFormatId)
                .orElseThrow(() -> {
                    logger.warn("ScreenFormat with id '{}' not found.", screenFormatId);
                    throw new NotFound(String.format("ScreenFormat with id %s not found", screenFormatId));
                });
    }

    public ScreenFormat add(ScreenFormatDto screenFormatDto) {
        logger.info("ScreenFormat with height '{}', width '{}' and room ids '{}' created.",
                screenFormatDto.screenHeight(), screenFormatDto.screenWidth(), screenFormatDto.roomIds());
        return this.screenFormatRepository.save(ScreenFormat.builder()
                .name(screenFormatDto.name())
                .screenHeight(screenFormatDto.screenHeight())
                .screenWidth(screenFormatDto.screenWidth())
                .rooms(new HashSet<>())
                .build());
    }

    public ScreenFormat update(String id, ScreenFormatDto screenFormatDto) {
        ScreenFormat found = this.findById(id);
        found.setName(screenFormatDto.name());
        found.setScreenHeight(screenFormatDto.screenHeight());
        found.setScreenWidth(screenFormatDto.screenWidth());
        found.setRooms(new HashSet<>());
        logger.info("ScreenFormat with id '{}' updated.", id);
        return this.screenFormatRepository.save(found);
    }

    public void delete(String screenFormatId) {
        if (!screenFormatRepository.existsById(screenFormatId)) {
            logger.warn("ScreenFormat with id '{}' does not exist.", screenFormatId);
            throw new NotFound(String.format("ScreenFormat with id %s does not exist", screenFormatId));
        }
        logger.info("ScreenFormat with id '{}' deleted.", screenFormatId);
        this.screenFormatRepository.deleteById(screenFormatId);
    }
}
