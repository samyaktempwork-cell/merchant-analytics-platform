package com.merchant.analytics.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transaction {

    private String transactionId;
    private String merchantId;
    private BigDecimal amount;
    private CardBrand cardBrand;
    private TransactionStatus status;
    private String declineReasonCode;
    private OffsetDateTime transactionDate;

    public Transaction() {
    }

    public Transaction(String transactionId,
                       String merchantId,
                       BigDecimal amount,
                       CardBrand cardBrand,
                       TransactionStatus status,
                       String declineReasonCode,
                       OffsetDateTime transactionDate) {
        this.transactionId = transactionId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.cardBrand = cardBrand;
        this.status = status;
        this.declineReasonCode = declineReasonCode;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CardBrand getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(CardBrand cardBrand) {
        this.cardBrand = cardBrand;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getDeclineReasonCode() {
        return declineReasonCode;
    }

    public void setDeclineReasonCode(String declineReasonCode) {
        this.declineReasonCode = declineReasonCode;
    }

    public OffsetDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(OffsetDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}
