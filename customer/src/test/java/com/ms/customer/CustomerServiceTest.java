package com.ms.customer;

import com.ms.customer.customer.entity.Customer;
import com.ms.customer.customer.entity.dto.CustomerDto;
import com.ms.customer.customer.service.CustomerService;
import com.ms.customer.shared.exceptions.BadRequest;
import com.ms.customer.shared.exceptions.NotFound;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;
    private Customer createdCustomer;

    @Before
    @Transactional
    public void setUp() {

        createdCustomer = this.customerService.add(CustomerDto.builder()
                .email("test@mail.com")
                .password("12345678")
                .build());
    }

    @Test
    @Transactional
    public void createCustomer() {
        Assert.assertEquals(createdCustomer, customerService.findById(createdCustomer.getId()));
    }

    @Test
    @Transactional
    public void createCustomerWithTakenEmail_throwsBadRequest() {
        Assert.assertThrows(BadRequest.class, () -> this.customerService.add(CustomerDto.builder()
                .email("prueba@mail.com")
                .password("12345678")
                .build()));
    }

    @Test
    @Transactional
    public void createCustomerWithWeakPassword_throwsBadRequest() {
        Assert.assertThrows(BadRequest.class, () -> this.customerService.add(CustomerDto.builder()
                .email("new@mail.com")
                .password("123")
                .build()));
    }

    @Test
    @Transactional
    public void updateCustomer() {
        final String updated_password = "updated password";
        this.customerService.update(createdCustomer.getId(), CustomerDto.builder()
                .password(updated_password)
                .build());
        Assert.assertEquals(updated_password, this.customerService.findById(this.createdCustomer.getId()).getPassword());
    }


    @Test
    @Transactional
    public void updateNonExistentCustomer_throwsNotFound() {
        Assert.assertThrows(NotFound.class, () -> this.customerService.update("non_existent_id",
                CustomerDto.builder()
                        .email(createdCustomer.getEmail())
                        .password(createdCustomer.getPassword())
                        .build()));
    }

    @Test
    @Transactional
    public void updateCustomerWithTakenEmail_throwsBadRequest() {
        final String shared_email = "shared email";
        final Customer newCustomer = this.customerService.add(CustomerDto.builder()
                .email(shared_email)
                .password("1234567890")
                .build());
        Assert.assertEquals(this.customerService.findById(newCustomer.getId()).getEmail(), shared_email);
        Assert.assertThrows(BadRequest.class,
                () -> this.customerService.update(createdCustomer.getId(), CustomerDto.builder()
                        .email(shared_email)
                        .build()));
    }

    @Test
    @Transactional
    public void deleteCustomer() {
        this.customerService.delete(createdCustomer.getId());
        Assert.assertThrows(NotFound.class, () -> this.customerService.findById(createdCustomer.getId()));
    }

    public void deleteNonExistentCustomer_throwsNotFound() {
        Assert.assertThrows(NotFound.class, () -> this.customerService.findById("non_existent_id"));
    }
}
