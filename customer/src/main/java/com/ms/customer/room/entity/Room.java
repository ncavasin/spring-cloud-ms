package com.ms.customer.room.entity;

import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room extends TimeTrackable {
    @Column(nullable = false)
    protected String name;
    //TODO: protected List<Seat> seats;
    //TODO: bidirectional List<Show> shows;
}
