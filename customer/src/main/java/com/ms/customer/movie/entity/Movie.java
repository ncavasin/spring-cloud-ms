package com.ms.customer.movie.entity;

import com.ms.customer.shared.entities.TimeTrackable;
import com.ms.customer.show.entity.Show;
import com.ms.customer.topic.entity.Topic;
import lombok.*;

import javax.persistence.*;
import java.time.Duration;
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
    protected Duration durationInMinutes;
    protected String classification;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "movie")
    protected Set<Show> shows;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "movie_topic",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    protected Set<Topic> topics;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title) && rating.equals(movie.rating) && Objects.equals(synopsis, movie.synopsis) && durationInMinutes.equals(movie.durationInMinutes) && Objects.equals(classification, movie.classification) && Objects.equals(shows, movie.shows) && Objects.equals(topics, movie.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, synopsis, durationInMinutes, classification);
    }
}
