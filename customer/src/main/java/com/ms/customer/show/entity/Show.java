package com.ms.customer.show.entity;

import com.ms.customer.movie.entity.Movie;
import com.ms.customer.room.entity.Room;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Show extends TimeTrackable {
    @Column(nullable = false)
    protected Date date;

    @Column(nullable = false)
    protected Time beginTime;

    @Column(nullable = false)
    protected Time endTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Movie movie;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Show show)) return false;
        return getDate().equals(show.getDate()) && getBeginTime().equals(show.getBeginTime()) && getEndTime().equals(show.getEndTime()) && getMovie().equals(show.getMovie()) && getRoom().equals(show.getRoom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getBeginTime(), getEndTime(), getMovie(), getRoom());
    }
}
