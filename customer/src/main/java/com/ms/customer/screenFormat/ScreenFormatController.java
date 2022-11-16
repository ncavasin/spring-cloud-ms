package com.ms.customer.screenFormat;

import com.ms.customer.screenFormat.dto.ScreenFormatConverter;
import com.ms.customer.screenFormat.dto.ScreenFormatDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/format")
public record ScreenFormatController(ScreenFormatService screenFormatService) {

    @GetMapping
    public List<ScreenFormatDto> getAll() {
        return ScreenFormatConverter.convert(this.screenFormatService.getAll());
    }

    @GetMapping("/{screenFormatId}")
    public ScreenFormatDto getById(@PathVariable("screenFormatId") String screenFormatId) {
        return ScreenFormatConverter.convert(this.screenFormatService.getById(screenFormatId));
    }

    @PostMapping
    public ScreenFormatDto add(@Validated @RequestBody ScreenFormatDto screenFormatDto) {
        return ScreenFormatConverter.convert(screenFormatService.add(ScreenFormatConverter.convert(screenFormatDto)));
    }

    @PatchMapping("/{screenFormatId}")
    public ScreenFormatDto update(@PathVariable("screenFormatId") String screenFormatId, @Validated @RequestBody ScreenFormatDto screenFormatDto) {
        return ScreenFormatConverter.convert(screenFormatService.update(screenFormatId, ScreenFormatConverter.convert(screenFormatDto)));
    }

    @DeleteMapping("/{screenFormatId}")
    public void delete(@PathVariable("screenFormatId") String screenFormatId) {
        this.screenFormatService.delete(screenFormatId);
    }
}
