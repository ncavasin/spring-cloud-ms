package com.ms.customer.screenFormat.entity;

import com.ms.customer.room.entity.Room;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScreenFormat extends TimeTrackable {
    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected Double screenHeight;

    @Column(nullable = false)
    protected Double screenWidth;

    @OneToMany(mappedBy = "screenFormat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    protected Set<Room> rooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenFormat that = (ScreenFormat) o;
        return name.equals(that.name) && screenHeight.equals(that.screenHeight) && screenWidth.equals(that.screenWidth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, screenHeight, screenWidth);
    }
}
