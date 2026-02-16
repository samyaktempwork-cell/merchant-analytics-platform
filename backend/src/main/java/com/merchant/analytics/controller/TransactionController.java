package com.merchant.analytics.controller;

import com.merchant.analytics.model.Transaction;
import com.merchant.analytics.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/api/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }
}
