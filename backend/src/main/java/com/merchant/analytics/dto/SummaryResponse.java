package com.merchant.analytics.dto;

import java.util.Map;

public class SummaryResponse {

    private long totalTransactions;
    private long totalApproved;
    private long totalDeclined;
    private Map<String, Long> byCardBrand;
    private Map<String, Long> byDeclineReason;

    public SummaryResponse(long totalTransactions,
                           long totalApproved,
                           long totalDeclined,
                           Map<String, Long> byCardBrand,
                           Map<String, Long> byDeclineReason) {
        this.totalTransactions = totalTransactions;
        this.totalApproved = totalApproved;
        this.totalDeclined = totalDeclined;
        this.byCardBrand = byCardBrand;
        this.byDeclineReason = byDeclineReason;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }

    public long getTotalApproved() {
        return totalApproved;
    }

    public long getTotalDeclined() {
        return totalDeclined;
    }

    public Map<String, Long> getByCardBrand() {
        return byCardBrand;
    }

    public Map<String, Long> getByDeclineReason() {
        return byDeclineReason;
    }
}
