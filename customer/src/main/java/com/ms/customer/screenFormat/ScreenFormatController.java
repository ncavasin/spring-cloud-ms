package com.ms.customer.screenFormat;

import com.ms.customer.screenFormat.entity.dto.ScreenFormatConverter;
import com.ms.customer.screenFormat.entity.dto.ScreenFormatDto;
import com.ms.customer.screenFormat.service.ScreenFormatService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/format")
public record ScreenFormatController(ScreenFormatService screenFormatService) {

    @GetMapping("/all")
    public Set<ScreenFormatDto> getAll() {
        return ScreenFormatConverter.convert(this.screenFormatService.findAll());
    }

    @GetMapping("/{screenFormatId}")
    public ScreenFormatDto getById(@PathVariable("screenFormatId") String screenFormatId) {
        return ScreenFormatConverter.convert(this.screenFormatService.findById(screenFormatId));
    }

    @PostMapping
    public ScreenFormatDto add(@Validated @RequestBody ScreenFormatDto screenFormatDto) {
        return ScreenFormatConverter.convert(screenFormatService.add(screenFormatDto));
    }

    @PatchMapping("/{screenFormatId}")
    public ScreenFormatDto update(@PathVariable("screenFormatId") String screenFormatId,
                                  @Validated @RequestBody ScreenFormatDto screenFormatDto) {
        return ScreenFormatConverter.convert(screenFormatService.update(screenFormatId, screenFormatDto));
    }

    @DeleteMapping("/{screenFormatId}")
    public void delete(@PathVariable("screenFormatId") String screenFormatId) {
        this.screenFormatService.delete(screenFormatId);
    }
}
