package com.ms.customer.seat;

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
    protected String row;

    @Column(nullable = false)
    protected Integer column;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatNaturalId that)) return false;
        return row.equals(that.row) && column.equals(that.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
