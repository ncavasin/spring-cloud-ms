package com.ms.customer;


import com.ms.customer.room.entity.Room;
import com.ms.customer.room.entity.dto.RoomDto;
import com.ms.customer.room.repository.RoomRepository;
import com.ms.customer.room.service.RoomService;
import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.SeatNaturalId;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.service.SeatService;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SeatServiceTest {

    @Autowired
    private SeatService seatService;

    @Mock
    private RoomService roomService;

    @MockBean
    private RoomRepository roomRepository;

    private Room mockedRoom;
    private Room fakeRoom;
    private RoomDto mockedDtoRoom;
    private SeatNaturalId createdSeatNaturalId;
    private Seat createdSeat;


    @Before
    @Transactional
    public void setUp() {
        this.createdSeatNaturalId = SeatNaturalId.builder()
                .seatRow("A")
                .seatColumn(1)
                .build();
        this.mockedDtoRoom = RoomDto.builder()
                .name("Room #001")
                .branchId(null)
                .screenFormatId(null)
                .build();
        this.mockedRoom = Room.builder()
                .name("ROOM #001")
                .screenFormat(null)
                .seats(null)
                .branch(null)
                .build();
        mockedRoom.setId("NON_EXISTENT");
        when(roomService.add(any(RoomDto.class))).thenReturn(mockedRoom);
        when(roomService.findById(any(String.class))).thenReturn(mockedRoom);
        this.fakeRoom = this.roomService.add(this.mockedDtoRoom);
        when(roomRepository.findById(any(String.class))).thenReturn(Optional.of(this.mockedRoom));
    }

    @Test
    @Transactional
    public void createSeat() {
        this.createdSeat = this.seatService.add(SeatDto.builder()
                .roomId(fakeRoom.getId())
                .seatNaturalId(this.createdSeatNaturalId)
                .build());
        Assert.assertEquals(this.createdSeat, this.seatService.findById(this.createdSeat.getId()));
    }

    @Test
    @Transactional
    public void createSeatRepeated_throwsBadRequest() {

    }

    @Test
    @Transactional
    public void createSeatWithNonExistentRoom_throwsBadRequest() {
        Assert.assertThrows(NotFound.class, () -> this.createdSeat = this.seatService.add(SeatDto.builder()
                .seatNaturalId(this.createdSeatNaturalId)
                .roomId("NON_EXISTENT_ROOM")
                .build()));
    }


    @Test
    @Transactional
    public void createSeatNegativeColumn_throwsBadRequest() {
        final SeatNaturalId invalidColumnSeatNaturalId = SeatNaturalId.builder()
                .seatColumn(-20)
                .seatRow("A")
                .build();
        Assert.assertThrows(NotFound.class, () -> this.createdSeat = this.seatService.add(SeatDto.builder()
                .seatNaturalId(invalidColumnSeatNaturalId)
                .roomId("NON_EXISTENT_ROOM")
                .build()));
    }

    @Test
    @Transactional
    public void findByNaturalId() {
        final Seat found = this.seatService.findByNaturalId(this.createdSeat.getSeatNaturalId());
    }

    @Test
    @Transactional
    public void deleteSeat() {
        this.seatService.delete(this.createdSeat.getId());
        Assert.assertThrows(NotFound.class, () -> this.seatService.findById(this.createdSeat.getId()));
    }

    @Test
    @Transactional
    public void deleteNonExistentSeat_throwsNotFound() {
        Assert.assertThrows(NotFound.class, () -> this.seatService.delete("NON_EXISTENT_SEAT_ID"));
    }
}

