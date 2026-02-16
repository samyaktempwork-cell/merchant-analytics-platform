package com.merchant.analytics.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merchant.analytics.model.Transaction;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Service
public class TransactionService {

    private List<Transaction> transactions;

    @PostConstruct
    public void loadTransactions() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.findAndRegisterModules();

            InputStream inputStream =
                    getClass().getResourceAsStream("/transactions.json");

            transactions = mapper.readValue(
                    inputStream,
                    new TypeReference<List<Transaction>>() {}
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to load transactions.json", e);
        }
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }
}
