package com.ms.customer.topic;

import com.ms.customer.topic.dto.TopicConverter;
import com.ms.customer.topic.dto.TopicDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topic")
public record TopicController(TopicService topicService) {

    @GetMapping("/all")
    public List<TopicDto> getAll() {
        return TopicConverter.convert(this.topicService.findAll());
    }

    @GetMapping("/{topicId}")
    public TopicDto getById(@PathVariable("topicId") String topicId) {
        return TopicConverter.convert(this.topicService.findById(topicId));
    }

    @GetMapping("/name/{name}")
    public TopicDto getByName(@PathVariable("name") String name) {
        return TopicConverter.convert(this.topicService.findByName(name));
    }

    @PostMapping()
    public TopicDto add(@Validated @RequestBody TopicDto topicDto) {
        return TopicConverter.convert(this.topicService.add(TopicConverter.convert(topicDto)));
    }

    @PatchMapping("/{topicId}")
    public TopicDto update(@PathVariable("topicId") String topicId, @Validated @RequestBody TopicDto topicDto) {
        return TopicConverter.convert(this.topicService.update(topicId, TopicConverter.convert(topicDto)));
    }

    @DeleteMapping("/{topicId}")
    public void delete(@PathVariable("topicId") String topicId) {
        this.topicService.delete(topicId);
    }
}
