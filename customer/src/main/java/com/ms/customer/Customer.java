package com.ms.customer;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Data
@Builder
public class Customer {
    private final BigInteger id;
    private final String firstName;
    private final String lastName;
    private final String email;
}
