package com.merchant.analytics.service;

import com.merchant.analytics.dto.SummaryResponse;
import com.merchant.analytics.model.Transaction;
import com.merchant.analytics.model.TransactionStatus;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SummaryService {

    private final TransactionService transactionService;

    public SummaryService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public SummaryResponse getMonthToDateSummary(
            String cardBrand,
            String status,
            String declineReasonCode
    ) {

        YearMonth currentMonth = YearMonth.now();

        List<Transaction> filtered =
                transactionService
                        .getFilteredTransactions(cardBrand, status, declineReasonCode)
                        .stream()
                        .filter(t -> YearMonth.from(t.getTransactionDate()).equals(currentMonth))
                        .toList();

        return buildSummary(filtered);
    }

    public Map<String, SummaryResponse> getMonthlySummary(
            String cardBrand,
            String status,
            String declineReasonCode
    ) {

        List<Transaction> filtered =
                transactionService
                        .getFilteredTransactions(cardBrand, status, declineReasonCode);

        Map<YearMonth, List<Transaction>> grouped =
                filtered.stream()
                        .collect(Collectors.groupingBy(
                                t -> YearMonth.from(t.getTransactionDate())
                        ));

        return grouped.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().toString(),
                        e -> buildSummary(e.getValue())
                ));
    }

    private SummaryResponse buildSummary(List<Transaction> transactions) {

        long totalTransactions = transactions.size();

        long totalApproved = transactions.stream()
                .filter(t -> t.getStatus() == TransactionStatus.APPROVED)
                .count();

        long totalDeclined = transactions.stream()
                .filter(t -> t.getStatus() == TransactionStatus.DECLINED)
                .count();

        Map<String, Long> byCardBrand =
                transactions.stream()
                        .collect(Collectors.groupingBy(
                                t -> t.getCardBrand().name(),
                                Collectors.counting()
                        ));

        Map<String, Long> byDeclineReason =
                transactions.stream()
                        .filter(t -> t.getDeclineReasonCode() != null)
                        .collect(Collectors.groupingBy(
                                Transaction::getDeclineReasonCode,
                                Collectors.counting()
                        ));

        return new SummaryResponse(
                totalTransactions,
                totalApproved,
                totalDeclined,
                byCardBrand,
                byDeclineReason
        );
    }
}
