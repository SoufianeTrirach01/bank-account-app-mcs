package com.example.accountservice.client;


import com.example.accountservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")

public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customer-service",fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);
    @GetMapping("/customers")
    @CircuitBreaker(name = "customer-service",fallbackMethod = "getDefaultCustomer")
    List<Customer> AllCustomer();


    default Customer getDefaultCustomer(Long id, Exception e) {
        return Customer.builder()
                .id(id)
                .firstName("Source not available")
                .lastName("Source Not Available")
                .build();
    }
    default List<Customer> getAllCustomer(Exception exception){
        //list vide
        return List.of();
    }
    }
