package com.ms.customer.customer;

import com.ms.customer.customer.entity.dto.CustomerConverter;
import com.ms.customer.customer.entity.dto.CustomerDto;
import com.ms.customer.customer.service.CustomerServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public record CustomerController(CustomerServiceImpl customerServiceImpl) {

    @GetMapping("/all")
    public List<CustomerDto> getAll() {
        return CustomerConverter.convert(this.customerServiceImpl.findAll());
    }


    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable("customerId") String customerId) {
        return CustomerConverter.convert(this.customerServiceImpl.findById(customerId));
    }

    @PostMapping()
    public CustomerDto addCustomer(@Validated @RequestBody CustomerDto customerDto) {
        return CustomerConverter.convert(this.customerServiceImpl.add(customerDto));
    }

    @PatchMapping("/{customerId}")
    public CustomerDto updateCustomer(@PathVariable("customerId") String customerId, @Validated @RequestBody CustomerDto customerDto) {
        return CustomerConverter.convert(customerServiceImpl.update(customerId, customerDto));
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") String customerId) {
        this.customerServiceImpl.delete(customerId);
    }

}
