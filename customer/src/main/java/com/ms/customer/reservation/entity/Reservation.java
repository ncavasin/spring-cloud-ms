package com.ms.customer.reservation.entity;

import com.ms.customer.customer.entity.Customer;
import com.ms.customer.seat.entity.Seat;
import com.ms.customer.shared.entities.TimeTrackable;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation extends TimeTrackable {

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "customer_id")
    protected Customer customer;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    protected Set<Seat> seats;
}
