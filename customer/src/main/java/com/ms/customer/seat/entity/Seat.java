package com.ms.customer.seat.entity;

import com.ms.customer.seat.SeatNaturalId;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seat extends TimeTrackable {
    @EmbeddedId
    protected SeatNaturalId seatNaturalId;
}
