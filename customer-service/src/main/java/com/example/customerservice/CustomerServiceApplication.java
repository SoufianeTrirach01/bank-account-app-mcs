package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.rep.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner cmd(CustomerRepository customerRepository){
		return args -> {
			List<Customer>list=List.of(
				Customer.builder()
							.email("strirah011@gmail.com")
							.firstName("soufiane")
							.lastName("trirach")
							.build(),
			Customer.builder()
					.email("strirah011@gmail.com")
					.firstName("Mohamed")
					.lastName("Mohamed")
					.build()
			);
			customerRepository.saveAll(list	);
		};
	}
}
