package com.ms.customer.customer;

import com.ms.customer.customer.dto.CustomerConverter;
import com.ms.customer.customer.dto.CustomerCreationDto;
import com.ms.customer.customer.dto.CustomerDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public record CustomerController(CustomerService customerService) {

    @GetMapping("/all")
    public List<CustomerDto> getAll() {
        return CustomerConverter.convert(this.customerService.getAll());
    }


    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable("customerId") String customerId) {
        return CustomerConverter.convert(this.customerService.getCustomerById(customerId));
    }

    @PostMapping()
    public CustomerDto addCustomer(@Validated @RequestBody CustomerCreationDto customerCreationDto) {
        return CustomerConverter.convert(this.customerService.createCustomer(customerCreationDto));
    }

    @PatchMapping("/{customerId}")
    public CustomerDto updateCustomer(@PathVariable("customerId") String customerId, @Validated @RequestBody CustomerDto customerDto) {
        return CustomerConverter.convert(this.customerService.updateCustomer(customerId, customerDto));
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") String customerId) {
        this.customerService.deleteCustomer(customerId);
    }

}
