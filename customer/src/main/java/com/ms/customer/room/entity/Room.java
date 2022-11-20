package com.ms.customer.room.entity;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.screenFormat.entity.ScreenFormat;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Objects;

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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected ScreenFormat screenFormat;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return getName().equals(room.getName()) && getBranch().equals(room.getBranch());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBranch());
    }
}
