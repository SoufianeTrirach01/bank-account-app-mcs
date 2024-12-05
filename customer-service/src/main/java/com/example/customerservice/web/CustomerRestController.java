package com.example.customerservice.web;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.rep.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class CustomerRestController {
    private CustomerRepository customerRepository;
@GetMapping("customers")
    public List<Customer> allCustomer(){
        return customerRepository.findAll();
    }

    @GetMapping("customers/{id}")
    public Customer customerById(@PathVariable("id")  Long id){
        return customerRepository.findById(id).get();
}
}
