package com.ms.ticket;

import com.ms.customer.Customer;
import com.ms.shared.entities.TimeTrackable;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket extends TimeTrackable {
    @Column(nullable = false)
    protected BigDecimal price;

    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private Customer customer;

    // protected Payment payment;

}
