package com.ms.customer.seat.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SeatNaturalId implements Serializable {
    @Column(nullable = false)
    protected String seatRow;

    @Column(nullable = false)
    protected Integer seatColumn;

    @Column
    protected boolean reserved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatNaturalId that)) return false;
        return seatRow.equals(that.seatRow) && seatColumn.equals(that.seatColumn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seatRow, seatColumn);
    }
}
