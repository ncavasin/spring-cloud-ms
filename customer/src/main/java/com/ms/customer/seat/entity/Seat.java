package com.ms.customer.seat.entity;

import com.ms.customer.room.entity.Room;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return seatNaturalId.equals(seat.seatNaturalId) && room.equals(seat.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatNaturalId, room);
    }
}
