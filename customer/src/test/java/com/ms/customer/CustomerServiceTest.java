package com.ms.customer;

import com.ms.customer.customer.entity.Customer;
import com.ms.customer.customer.entity.dto.CustomerDto;
import com.ms.customer.customer.service.CustomerService;
import com.ms.customer.shared.exceptions.BadRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
public class CustomerServiceTest extends TestContainerBase {

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

    public void updateCustomer() {

    }

    public void updateNonExistentCustomer_throwsNotFound() {

    }

    public void updateCustomerWithTakenEmail_throwsBadRequest() {

    }

    public void deleteCustomer() {

    }

    public void deleteNonExistentCustomer_throwsNotFound() {

    }
}
