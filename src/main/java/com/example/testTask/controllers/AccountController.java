package com.example.testTask.controllers;

import com.example.testTask.Service.AccountServiceImpl;
import com.example.testTask.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService = new AccountServiceImpl();

    @GetMapping("/account/{id}")
    public Long getAmount(@PathVariable Integer id) {
        return accountService.getAmount(id);
    }

    @PutMapping("/account/{id}/{amount}")
    public void addAmount(@PathVariable int id, @PathVariable long amount) {
        accountService.addAmount(id, amount);
    }
}
