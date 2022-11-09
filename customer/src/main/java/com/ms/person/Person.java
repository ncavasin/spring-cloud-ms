package com.ms.person;

import com.ms.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Entity;
import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person extends TimeTrackable {
    protected String firstName;
    protected String lastName;
    protected Date birthDate;
}
