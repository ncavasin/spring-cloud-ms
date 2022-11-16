package com.ms.customer.screenFormat;

import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record ScreenFormatService(ScreenFormatRepository screenFormatRepository) {
    public List<ScreenFormat> getAll() {
        return this.screenFormatRepository.findAll();
    }

    public ScreenFormat getById(String screenFormatId) {
        return this.screenFormatRepository.findById(screenFormatId)
                .orElseThrow(() -> new NotFound(String.format("ScreenFormat with id %s not found", screenFormatId)));
    }

    public ScreenFormat add(ScreenFormat screenFormat) {
        return this.screenFormatRepository.save(screenFormat);
    }

    public ScreenFormat update(String screenFormatId, ScreenFormat screenFormat) {
        checkScreenFormatExists(screenFormatId);
        screenFormat.setId(screenFormatId);
        return this.screenFormatRepository.save(screenFormat);
    }

    private void checkScreenFormatExists(String screenFormatId) {
        if (!screenFormatRepository.existsById(screenFormatId)) {
            throw new BadRequest(String.format("ScreenFormat with id %s does not exist", screenFormatId));
        }
    }

    public void delete(String screenFormatId) {
        checkScreenFormatExists(screenFormatId);
        this.screenFormatRepository.deleteById(screenFormatId);
    }
}
