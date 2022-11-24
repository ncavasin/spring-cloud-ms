package com.ms.customer.seat.entity;

import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seat extends TimeTrackable implements Serializable {
    @Embedded
    @NaturalId
    protected SeatNaturalId seatNaturalId;

    @Column
    protected boolean reserved;
}
