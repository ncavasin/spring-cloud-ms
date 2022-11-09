package com.ms.customer;

import com.ms.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer extends TimeTrackable {
    @Column(unique = true)
    private String email;
    private String password;
}
