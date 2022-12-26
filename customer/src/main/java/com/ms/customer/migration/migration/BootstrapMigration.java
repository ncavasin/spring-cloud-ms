package com.ms.customer.migration.migration;

import com.ms.customer.branch.entity.dto.BranchDto;
import com.ms.customer.branch.service.BranchService;
import com.ms.customer.customer.entity.Customer;
import com.ms.customer.customer.entity.dto.CustomerDto;
import com.ms.customer.customer.service.CustomerService;
import com.ms.customer.migration.StartUpMigration;
import com.ms.customer.movie.entity.dto.MovieDto;
import com.ms.customer.movie.service.MovieService;
import com.ms.customer.room.entity.dto.RoomDto;
import com.ms.customer.room.service.RoomService;
import com.ms.customer.screenFormat.entity.dto.ScreenFormatDto;
import com.ms.customer.screenFormat.service.ScreenFormatService;
import com.ms.customer.seat.entity.SeatNaturalId;
import com.ms.customer.seat.entity.dto.SeatDto;
import com.ms.customer.seat.service.SeatService;
import com.ms.customer.topic.entity.dto.TopicDto;
import com.ms.customer.topic.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Set;


@Slf4j
@Component
public record BootstrapMigration(Logger logger,
                                 CustomerService customerService,
                                 BranchService branchService,
                                 ScreenFormatService screenFormatService,
                                 TopicService topicService,
                                 RoomService roomService,
                                 SeatService seatService,
                                 MovieService movieService
) implements StartUpMigration {

    @Override
    public void run(ApplicationReadyEvent applicationReadyEvent) {

        final Customer customer1 = this.customerService.add(CustomerDto.builder()
                .email("prueba@mail.com")
                .password("12345678")
                .build());
        final Customer customer2 = this.customerService.add(CustomerDto.builder()
                .email("prueba2@mail.com")
                .password("12345678")
                .build());

        final String lujan = this.branchService.add(BranchDto.builder()
                        .name("Lujan")
                        .zipCode("6700")
                        .build())
                .getId();
        final String pilar = this.branchService.add(BranchDto.builder()
                        .name("Pilar")
                        .zipCode("1629")
                        .build())
                .getId();
        final String moreno = this.branchService.add(BranchDto.builder()
                        .name("Moreno")
                        .zipCode("1738")
                        .build())
                .getId();
        final String moron = this.branchService.add(BranchDto.builder()
                        .name("Moron")
                        .zipCode("1708")
                        .build())
                .getId();

        final String classic = this.screenFormatService.add(ScreenFormatDto.builder()
                        .name("Classic")
                        .screenWidth(10D)
                        .screenHeight(6.5D)
                        .build())
                .getId();
        final String xd = this.screenFormatService.add(ScreenFormatDto.builder()
                        .name("XD")
                        .screenWidth(15D)
                        .screenHeight(8.5D)
                        .build())
                .getId();
        final String imax = this.screenFormatService.add(ScreenFormatDto.builder()
                        .name("IMAX")
                        .screenWidth(29D)
                        .screenHeight(21D)
                        .build())
                .getId();

        final String drama = this.topicService.add(TopicDto.builder()
                        .name("DRAMA")
                        .build())
                .getId();
        final String fantasy = this.topicService.add(TopicDto.builder()
                        .name("FANTASY")
                        .build())
                .getId();
        final String scifi = this.topicService.add(TopicDto.builder()
                        .name("SCIFI")
                        .build())
                .getId();
        final String comedy = this.topicService.add(TopicDto.builder()
                        .name("COMEDY")
                        .build())
                .getId();
        final String terror = this.topicService.add(TopicDto.builder()
                        .name("TERROR")
                        .build())
                .getId();
        final String action = this.topicService.add(TopicDto.builder()
                        .name("ACTION")
                        .build())
                .getId();
        final String history = this.topicService.add(TopicDto.builder()
                        .name("HISTORY")
                        .build())
                .getId();
        final String based_on_true_events = this.topicService.add(TopicDto.builder()
                        .name("BASED ON TRUE EVENTS")
                        .build())
                .getId();

        final String room001 = this.roomService.add(RoomDto.builder()
                        .name("Room 001")
                        .branchId(lujan)
                        .screenFormatId(classic)
                        .build())
                .getId();
        final String room002 = this.roomService.add(RoomDto.builder()
                        .name("Room 002")
                        .branchId(lujan)
                        .screenFormatId(imax)
                        .build())
                .getId();
        final String room003 = this.roomService.add(RoomDto.builder()
                        .name("Room 003")
                        .branchId(lujan)
                        .screenFormatId(imax)
                        .build())
                .getId();
        final String room004 = this.roomService.add(RoomDto.builder()
                        .name("Room 004")
                        .branchId(lujan)
                        .screenFormatId(xd)
                        .build())
                .getId();
        final String room005 = this.roomService.add(RoomDto.builder()
                        .name("Room 005")
                        .branchId(pilar)
                        .screenFormatId(xd)
                        .build())
                .getId();
        final String room006 = this.roomService.add(RoomDto.builder()
                        .name("Room 006")
                        .branchId(pilar)
                        .screenFormatId(imax)
                        .build())
                .getId();
        final String room007 = this.roomService.add(RoomDto.builder()
                        .name("Room 007")
                        .branchId(moreno)
                        .screenFormatId(classic)
                        .build())
                .getId();
        final String room008 = this.roomService.add(RoomDto.builder()
                        .name("Room 008")
                        .branchId(moron)
                        .screenFormatId(classic)
                        .build())
                .getId();
        final String room009 = this.roomService.add(RoomDto.builder()
                        .name("Room 009")
                        .branchId(moron)
                        .screenFormatId(classic)
                        .build())
                .getId();
        final String room010 = this.roomService.add(RoomDto.builder()
                        .name("Room 010")
                        .branchId(moron)
                        .screenFormatId(imax)
                        .build())
                .getId();

        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("A")
                        .seatColumn(1)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("A")
                        .seatColumn(2)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("A")
                        .seatColumn(3)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("A")
                        .seatColumn(4)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("A")
                        .seatColumn(5)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("B")
                        .seatColumn(1)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("B")
                        .seatColumn(2)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("B")
                        .seatColumn(3)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("B")
                        .seatColumn(4)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("B")
                        .seatColumn(5)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("C")
                        .seatColumn(1)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("C")
                        .seatColumn(2)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("C")
                        .seatColumn(3)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("C")
                        .seatColumn(4)
                        .build())
                .roomId(room001)
                .build());
        this.seatService.add(SeatDto.builder()
                .seatNaturalId(SeatNaturalId.builder()
                        .seatRow("C")
                        .seatColumn(5)
                        .build())
                .roomId(room001)
                .build());

        this.movieService.add(MovieDto.builder()
                .title("The lord of the Rings: The Fellowship of the Ring")
                .duration(Duration.ofMinutes(178L))
                .classification("PG-13")
                .topicIds(Set.of(action, drama, fantasy))
                .rating(8.8D)
                .synopsis("No synopsis by now")
                .build());
        this.movieService.add(MovieDto.builder()
                .title("The lord of the Rings: The Two Towers")
                .duration(Duration.ofMinutes(179L))
                .classification("PG-13")
                .topicIds(Set.of(action, drama, fantasy))
                .rating(8.8D)
                .synopsis("No synopsis by now")
                .build());
        this.movieService.add(MovieDto.builder()
                .title("The lord of the Rings: The Return of the King")
                .duration(Duration.ofMinutes(190))
                .classification("PG-13")
                .topicIds(Set.of(action, drama, fantasy))
                .rating(8.8D)
                .synopsis("No synopsis by now")
                .build());
    }

    @Override
    public String getName() {
        return "Bootstrap migration";
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
