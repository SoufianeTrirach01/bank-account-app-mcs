package com.example.accountservice.controller;

import com.example.accountservice.client.CustomerRestClient;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.model.Customer;
import com.example.accountservice.repo.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequestMapping("api/")
@RestController
@RequiredArgsConstructor
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;
    @GetMapping("accounts")
    public List<BankAccount> allAccounts(){
        List<BankAccount> allAccounts = bankAccountRepository.findAll();
        allAccounts.forEach(
                account->
                     account.setCustomer(customerRestClient.findCustomerById(account.getCustomerId())))   ;
        return
                allAccounts;
    }

    @GetMapping("accounts/{accountId}")
    public BankAccount customerById(@PathVariable("accountId")  String accountId){
        BankAccount bankAccount = bankAccountRepository.findById(accountId).get();
        Customer customer= customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
