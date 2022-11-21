package com.ms.customer.branch.entity;

import com.ms.customer.room.entity.Room;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch extends TimeTrackable {
    @Column(nullable = false, unique = true)
    protected String name;
    protected String zipCode;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    protected Set<Room> rooms = new java.util.LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch branch)) return false;
        return getName().equals(branch.getName()) && Objects.equals(getZipCode(), branch.getZipCode()) && getRooms().equals(branch.getRooms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getZipCode(), getRooms());
    }
}
