package com.pswied.osnabruck.middleware.service;

import com.pswied.osnabruck.middleware.model.TransactionRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class HashingServiceTest {

    private final HashingService hashingService = new HashingService();

    @Test
    void calculateHash_shouldBeDeterministic() {
        // Given
        TransactionRequest request1 = createSampleRequest();
        TransactionRequest request2 = createSampleRequest();

        // When
        String hash1 = hashingService.calculateHash(request1);
        String hash2 = hashingService.calculateHash(request2);

        // Then
        assertNotNull(hash1);
        assertEquals(hash1, hash2, "Hash should be deterministic for identical inputs");
    }

    @Test
    void calculateHash_shouldChangeWhenDataChanges() {
        // Given
        TransactionRequest request1 = createSampleRequest();
        TransactionRequest request2 = createSampleRequest();
        request2.setAmount(new BigDecimal("100.01")); // Slight change

        // When
        String hash1 = hashingService.calculateHash(request1);
        String hash2 = hashingService.calculateHash(request2);

        // Then
        assertNotEquals(hash1, hash2, "Hash should change when input data changes");
    }

    private TransactionRequest createSampleRequest() {
        TransactionRequest request = new TransactionRequest();
        request.setTransactionId("TX123");
        request.setSenderAccount("ACC1");
        request.setReceiverAccount("ACC2");
        request.setAmount(new BigDecimal("100.00"));
        request.setCurrency("USD");
        request.setTimestamp(Instant.parse("2023-10-27T10:00:00Z"));
        return request;
    }
}
