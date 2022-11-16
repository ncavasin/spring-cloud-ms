package com.ms.customer.topic;

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
public class Topic extends TimeTrackable {
    @Column(nullable = false)
    protected String name;

    //TODO: protected List<Movie> movies;
}
