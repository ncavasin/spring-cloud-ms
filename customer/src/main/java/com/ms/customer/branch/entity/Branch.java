package com.ms.customer.branch.entity;

import com.ms.customer.room.entity.Room;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<Room> rooms = new java.util.LinkedHashSet<>();
}
