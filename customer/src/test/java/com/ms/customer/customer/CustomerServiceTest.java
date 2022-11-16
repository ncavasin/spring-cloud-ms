package com.ms.customer.customer;

import com.ms.customer.customer.dto.CustomerConverter;
import com.ms.customer.customer.dto.CustomerDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
//@ActiveProfiles("mem")
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    private Customer createdCustomer;

    @Before
    @Transactional
    public void setUp() {
        this.createdCustomer = customerService.add(CustomerConverter.convert(CustomerDto.builder()
                .email("probandoemail@emailxyz.com")
                .password("superpassword")
                .build()));
    }

    @Test
    @Transactional
    public void createCustomer() {
        Assert.assertEquals(createdCustomer, customerService.getById(createdCustomer.getId()));
    }
}
