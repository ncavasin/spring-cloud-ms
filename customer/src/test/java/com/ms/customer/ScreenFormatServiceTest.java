package com.ms.customer;

import com.ms.customer.screenFormat.entity.ScreenFormat;
import com.ms.customer.screenFormat.entity.dto.ScreenFormatDto;
import com.ms.customer.screenFormat.service.ScreenFormatService;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class ScreenFormatServiceTest {
    @Autowired
    private ScreenFormatService screenFormatService;

    private ScreenFormat createdScreenFormat;

    @Before
    public void setUp() {
        createdScreenFormat = this.screenFormatService.add(ScreenFormatDto.builder()
                .name("TEST SCREEN")
                .screenHeight(15D)
                .screenWidth(150D)
                .build());
    }

    @Test
    @Transactional
    public void createScreenFormat() {
        Assert.assertEquals(createdScreenFormat, this.screenFormatService.findById(createdScreenFormat.getId()));
    }

    @Test
    @Transactional
    public void updateScreenFormatName() {
        final String newName = "UPDATED NAME";
        this.screenFormatService.update(this.createdScreenFormat.getId(), ScreenFormatDto.builder()
                .name(newName)
                .screenWidth(this.createdScreenFormat.getScreenWidth())
                .screenHeight(this.createdScreenFormat.getScreenHeight())
                .build());
        Assert.assertEquals(this.screenFormatService.findById(createdScreenFormat.getId()).getName(), newName);
    }

    @Test
    @Transactional
    public void updateScreenFormatWidth() {
        final Double newWidth = 5D;
        this.screenFormatService.update(this.createdScreenFormat.getId(), ScreenFormatDto.builder()
                .name(this.createdScreenFormat.getName())
                .screenHeight(this.createdScreenFormat.getScreenHeight())
                .screenWidth(newWidth)
                .build());
        Assert.assertEquals(this.screenFormatService.findById(createdScreenFormat.getId()).getScreenWidth(), newWidth);

    }

    @Test
    @Transactional
    public void updateScreenFormatInvalidWidth_throwsBadRequest() {
        final Double invalidWidth = -20D;
        Assert.assertThrows(BadRequest.class, () -> this.screenFormatService.update(this.createdScreenFormat.getId(),
                ScreenFormatDto.builder()
                        .name(this.createdScreenFormat.getName())
                        .screenHeight(this.createdScreenFormat.getScreenHeight())
                        .screenWidth(invalidWidth)
                        .build()));
    }

    @Test
    @Transactional
    public void updateScreenFormatHeight() {
        final Double newHeight = 20D;
        this.screenFormatService.update(this.createdScreenFormat.getId(), ScreenFormatDto.builder()
                .name(this.createdScreenFormat.getName())
                .screenWidth(this.createdScreenFormat.getScreenWidth())
                .screenHeight(newHeight)
                .build());
        Assert.assertEquals(this.screenFormatService.findById(createdScreenFormat.getId()).getScreenHeight(), newHeight);
    }

    @Test
    @Transactional
    public void updateScreenFormatInvalidHeight_throwsBadRequest() {
        final Double invalidHeight = -20D;
        Assert.assertThrows(BadRequest.class, () -> this.screenFormatService.update(this.createdScreenFormat.getId(),
                ScreenFormatDto.builder()
                        .name(this.createdScreenFormat.getName())
                        .screenHeight(invalidHeight)
                        .build()));
    }

    @Test
    @Transactional
    public void deleteScreenFormat() {
        this.screenFormatService.delete(createdScreenFormat.getId());
        Assert.assertThrows(NotFound.class, () -> this.screenFormatService.delete(createdScreenFormat.getId()));

    }

    @Test
    @Transactional
    public void deleteNonExistentScreenFormat_throwsNotFound() {
        Assert.assertThrows(NotFound.class, () -> this.screenFormatService.delete("non_existent_id"));
    }
}
