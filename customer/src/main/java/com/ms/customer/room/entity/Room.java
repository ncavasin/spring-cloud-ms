package com.ms.customer.room.entity;

import com.ms.customer.branch.entity.Branch;
import com.ms.customer.screenFormat.entity.ScreenFormat;
import com.ms.customer.seat.entity.Seat;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    protected Branch branch;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "room")
    protected Set<Seat> seats = new HashSet<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected ScreenFormat screenFormat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return getName().equals(room.getName()) && getBranch().equals(room.getBranch()) && getScreenFormat().equals(room.getScreenFormat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBranch(), getScreenFormat());
    }
}
