package com.ms.customer.screenFormat;

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
public class ScreenFormat extends TimeTrackable {
    @Column(nullable = false)
    protected String name;
    protected Double screenHeight;
    protected Double screenWidth;

    //TODO: protected List<Room> rooms;
}
