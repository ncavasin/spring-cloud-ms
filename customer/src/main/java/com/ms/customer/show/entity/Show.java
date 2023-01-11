package com.ms.customer.show.entity;

import com.ms.customer.movie.entity.Movie;
import com.ms.customer.room.entity.Room;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.time.LocalTime;
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
    protected LocalTime beginTime;

    @Column(nullable = false)
    protected LocalTime endTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Movie movie;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return beginTime.equals(show.beginTime) && endTime.equals(show.endTime) && movie.equals(show.movie) && room.equals(show.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginTime, endTime, movie, room);
    }

}
