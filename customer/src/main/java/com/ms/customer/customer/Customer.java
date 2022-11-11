package com.ms.customer.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer extends TimeTrackable {
    @Column(unique = true)
    protected String email;

    @JsonIgnore
    protected String password;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
//    @JoinTable(name = "customer_ticket",
//            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"))
//    @Fetch(FetchMode.JOIN)
//    protected Set<Ticket> tickets = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return getEmail().equals(customer.getEmail()) && getPassword().equals(customer.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }
}
