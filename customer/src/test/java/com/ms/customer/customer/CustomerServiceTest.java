//package com.ms.customer.customer;
//
//import com.ms.customer.TestContainerBase;
//import com.ms.customer.customer.entity.Customer;
//import com.ms.customer.customer.entity.dto.CustomerDto;
//import com.ms.customer.customer.service.CustomerService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@Testcontainers
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class CustomerServiceTest extends TestContainerBase {
//
//    @Value("")
//    private int port;
//
//    @Autowired
//    private CustomerService customerService;
//    private Customer createdCustomer;
//
//    @Container
//    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:12.4")
//        .withDatabaseName("testdb")
//        .withUsername("testuser")
//        .withPassword("testpass");
//
//    @Before
//    @Transactional
//    public void setUp() {
//        createdCustomer = this.customerService.add(CustomerDto.builder()
//                .email("prueba@mail.com")
//                .password("12345678")
//                .build());
//    }
//
//    @Test
//    @Transactional
//    public void createCustomer() {
//        Assert.assertEquals(createdCustomer, customerService.findById(createdCustomer.getId()));
//    }
//
//    public void createCustomerWithTakenEmail_throwsBadRequest() {
//
//    }
//
//    public void createCustomerWithWeakPassword_throwsBadRequest() {
//
//    }
//
//    public void updateCustomer() {
//
//    }
//
//    public void updateNonExistentCustomer_throwsNotFound() {
//
//    }
//
//    public void updateCustomerWithTakenEmail_throwsBadRequest() {
//
//    }
//
//    public void deleteCustomer() {
//
//    }
//
//    public void deleteNonExistentCustomer_throwsNotFound() {
//
//    }
//}
