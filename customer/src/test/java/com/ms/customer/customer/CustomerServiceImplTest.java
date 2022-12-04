package com.ms.customer.customer;

import com.ms.customer.customer.entity.Customer;
import com.ms.customer.customer.service.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
//@ActiveProfiles("mem")
@SpringBootTest
public class CustomerServiceImplTest {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    private Customer createdCustomer;

    @Ignore
    @Before
    @Transactional
    public void setUp() {
    }

    @Ignore
    @Test
    @Transactional
    public void createCustomer() {
        Assert.assertEquals(createdCustomer, customerServiceImpl.findById(createdCustomer.getId()));
    }
}
