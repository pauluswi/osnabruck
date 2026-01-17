package com.pswied.osnabruck.middleware.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VerificationRequest {

    @NotBlank(message = "Trace ID is required")
    private String traceId;

    @NotNull(message = "Original transaction data is required")
    @Valid
    private TransactionRequest transactionData;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public TransactionRequest getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(TransactionRequest transactionData) {
        this.transactionData = transactionData;
    }
}
