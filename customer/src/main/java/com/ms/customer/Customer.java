package com.ms.customer;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_sequence",
            sequenceName = "customer_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

}
