package com.ms.customer.screenFormat;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public record ScreenFormatService(ScreenFormatRepository screenFormatRepository, Logger logger) {
    public List<ScreenFormat> getAll() {
        return this.screenFormatRepository.findAll();
    }

    public ScreenFormat getById(String screenFormatId) {
        return this.screenFormatRepository.findById(screenFormatId)
                .orElseThrow(() -> {
                    logger.warn("ScreenFormat with id '{}' not found.", screenFormatId);
                    throw new NotFound(String.format("ScreenFormat with id %s not found", screenFormatId));
                });
    }

    public ScreenFormat add(ScreenFormat screenFormat) {
        logger.info("ScreenFormat with height '{}' and width '{}' created.", screenFormat.screenHeight, screenFormat.screenWidth);
        return this.screenFormatRepository.save(screenFormat);
    }

    public ScreenFormat update(String screenFormatId, ScreenFormat screenFormat) {
        checkScreenFormatExists(screenFormatId);
        screenFormat.setId(screenFormatId);
        logger.info("ScreenFormat with id '{}' updated.", screenFormatId);
        return this.screenFormatRepository.save(screenFormat);
    }

    public void delete(String screenFormatId) {
        checkScreenFormatExists(screenFormatId);
        logger.info("ScreenFormat with id '{}' deleted.", screenFormatId);
        this.screenFormatRepository.deleteById(screenFormatId);
    }

    private void checkScreenFormatExists(String screenFormatId) {
        if (!screenFormatRepository.existsById(screenFormatId)) {
            logger.warn("ScreenFormat with id '{}' does not exist.", screenFormatId);
            throw new BadRequest(String.format("ScreenFormat with id %s does not exist", screenFormatId));
        }
    }
}
