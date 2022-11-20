package com.ms.customer.room.entity;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room extends TimeTrackable {
    @Column(nullable = false)
    protected String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Branch branch;

    //TODO: protected List<Seat> seats;
    //TODO: bidirectional List<Show> shows;

}
