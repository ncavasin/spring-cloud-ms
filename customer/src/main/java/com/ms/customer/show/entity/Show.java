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

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Show extends TimeTrackable {
    @Column(nullable = false)
    protected Date date;

    protected Time beginTime;

    protected Time endTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Movie movie;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Room room;
}
