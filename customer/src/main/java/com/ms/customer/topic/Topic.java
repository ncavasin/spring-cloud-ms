package com.ms.customer.topic;

import com.ms.customer.movie.Movie;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topic extends TimeTrackable {
    @Column(nullable = false, unique = true)
    @NaturalId
    protected String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "topics_movie",
            joinColumns = @JoinColumn(name = "topic_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic topic)) return false;
        return getName().equals(topic.getName()) && Objects.equals(getMovies(), topic.getMovies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMovies());
    }
}
