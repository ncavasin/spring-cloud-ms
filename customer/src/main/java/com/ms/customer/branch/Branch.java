package com.ms.customer.branch;

import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch extends TimeTrackable {
    @Column(nullable = false)
    protected String name;
    protected String zipCode;
}
