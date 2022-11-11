package com.ms.customer.movie;

import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Time;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie extends TimeTrackable {
    @Column(nullable = false, unique = true)
    protected String title;
    protected Double rating;
    protected String synopsis;
    @Column(nullable = false)
    protected Time duration;
    protected String classification;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return getTitle().equals(movie.getTitle()) && Objects.equals(getRating(), movie.getRating()) && Objects.equals(getSynopsis(), movie.getSynopsis()) && getDuration().equals(movie.getDuration()) && Objects.equals(getClassification(), movie.getClassification());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getRating(), getSynopsis(), getDuration(), getClassification());
    }
}
