package com.merchant.analytics;

import com.merchant.analytics.model.Transaction;
import com.merchant.analytics.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
        transactionService.loadTransactions();
    }

    @Test
    void shouldFilterByCardBrand() {
        List<Transaction> result =
                transactionService.getFilteredTransactions("VISA", null, null);

        assertFalse(result.isEmpty());
        assertTrue(result.stream()
                .allMatch(t -> t.getCardBrand().name().equals("VISA")));
    }

    @Test
    void shouldFilterByStatus() {
        List<Transaction> result =
                transactionService.getFilteredTransactions(null, "DECLINED", null);

        assertFalse(result.isEmpty());
        assertTrue(result.stream()
                .allMatch(t -> t.getStatus().name().equals("DECLINED")));
    }

    @Test
    void shouldFilterByDeclineReason() {
        List<Transaction> result =
                transactionService.getFilteredTransactions(null, null, "01");

        assertFalse(result.isEmpty());
        assertTrue(result.stream()
                .allMatch(t -> "01".equals(t.getDeclineReasonCode())));
    }
}
