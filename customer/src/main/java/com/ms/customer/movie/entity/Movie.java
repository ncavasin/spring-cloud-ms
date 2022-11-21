package com.ms.customer.movie.entity;

import com.ms.customer.shared.entities.TimeTrackable;
import com.ms.customer.show.entity.Show;
import com.ms.customer.topic.entity.Topic;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie extends TimeTrackable {
    @Column(nullable = false, unique = true)
    protected String title;
    @Column(nullable = false)
    protected Double rating;
    protected String synopsis;
    @Column(nullable = false)
    protected Time duration;
    protected String classification;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "movie_show",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    protected Set<Show> shows;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "movie_topic",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    protected Set<Topic> topics;

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
