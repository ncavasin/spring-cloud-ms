package com.ms.customer.show;

import com.ms.customer.shared.converters.ShowConverter;
import com.ms.customer.show.entity.dto.ShowDto;
import com.ms.customer.show.service.ShowService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Set;

@RestController
@RequestMapping("/api/show")
public record ShowController(ShowService showService) {

    @GetMapping("/all")
    public Set<ShowDto> findAll() {
        return ShowConverter.convert(this.showService.findAll());
    }

    @GetMapping("/{showId}")
    public ShowDto findById(@PathVariable("showId") String id) {
        return ShowConverter.convert(this.showService.findById(id));
    }

    @GetMapping("/date/{date}")
    public Set<ShowDto> findByDate(@PathVariable("date") Date date) {
        return ShowConverter.convert(this.showService.findByDate(date));
    }

    @PostMapping
    public ShowDto add(@Validated @RequestBody ShowDto showDto) {
        return ShowConverter.convert(this.showService.add(showDto));
    }

    @PatchMapping("/{showId}")
    public ShowDto update(@PathVariable("showId") String id, @Validated @RequestBody ShowDto showDto) {
        return ShowConverter.convert(this.showService.update(id, showDto));
    }

    @DeleteMapping("/{showId}")
    public void delete(@PathVariable("showId") String id) {
        this.showService.delete(id);
    }
}
