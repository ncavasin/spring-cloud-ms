package com.ms.customer.screenFormat.entity;

import com.ms.customer.room.entity.Room;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.*;
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
    protected Double screenHeight;
    protected Double screenWidth;

    @OneToMany(mappedBy = "screenFormat", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    protected Set<Room> rooms;
}
