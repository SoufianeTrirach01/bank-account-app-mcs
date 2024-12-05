package com.example.accountservice;

import com.example.accountservice.client.CustomerRestClient;
import com.example.accountservice.entities.AccountType;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.repo.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BankAccountRepository accountRepository, CustomerRestClient customerRestClient){
		return args -> {
			customerRestClient.AllCustomer().forEach(
					customer -> {
						BankAccount bankAccount= BankAccount.builder()
								.accountId(UUID.randomUUID().toString())
								.balance(Math.random()*49909)
								.createdAt(LocalDate.now())
								.currency("MAD")
								.type(AccountType.CURRENT_ACCOUNT)
								.customerId(customer.getId())
								.build();
						BankAccount bankAccount1= BankAccount.builder()
								.accountId(UUID.randomUUID().toString())
								.balance(Math.random()*49909)
								.createdAt(LocalDate.now())
								.currency("MAD")
								.type(AccountType.SAVING_ACCOUNT)
								.customerId(customer.getId())
								.build();
						accountRepository.save(bankAccount1);
						accountRepository.save(bankAccount);
					}

			);



		};
	}

}
