package com.ms.customer.seat.entity;

import com.ms.customer.room.entity.Room;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seat extends TimeTrackable implements Serializable {
    @Embedded
    @NaturalId
    @Column(unique = true, nullable = false)
    protected SeatNaturalId seatNaturalId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected Timestamp selection;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected Timestamp confirmation;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Room room;
}
