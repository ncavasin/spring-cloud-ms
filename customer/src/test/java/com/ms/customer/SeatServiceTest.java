package com.ms.customer;


import com.ms.customer.room.entity.Room;
import com.ms.customer.room.repository.RoomRepository;
import com.ms.customer.room.service.RoomService;
import com.ms.customer.seat.entity.Seat;
import com.ms.customer.seat.entity.SeatNaturalId;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.repository.SeatRepository;
import com.ms.customer.seat.service.SeatService;
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

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private SeatRepository seatRepository;

    private Room mockedRoom;

    private SeatNaturalId createdSeatNaturalId;
    private Seat createdSeat;


    @Before
    public void setUp() {
        this.mockedRoom = Room.builder()
                .name("ROOM #001")
                .screenFormat(null)
                .seats(null)
                .branch(null)
                .build();

        this.createdSeatNaturalId = SeatNaturalId.builder()
                .seatRow("A")
                .seatColumn(1)
                .build();

//        when(roomRepository.save(any(Room.class))).thenReturn(this.mockedRoom);
        when(roomRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(this.mockedRoom));
        when(roomService.findById(any(String.class))).thenReturn(this.mockedRoom);

        this.createdSeat = this.seatService.add(SeatDto.builder()
                .seatNaturalId(createdSeatNaturalId)
                .roomId(this.mockedRoom.getId())
                .build());

        when(seatRepository.save(any(Seat.class))).thenReturn(this.createdSeat);
    }

    @Test
    @Transactional
    public void createSeat() {
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
                .seatNaturalId(createdSeatNaturalId)
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

