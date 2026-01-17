package com.pswied.osnabruck.middleware.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pswied.osnabruck.middleware.model.AuditRecordDto;
import com.pswied.osnabruck.middleware.model.TransactionRequest;
import com.pswied.osnabruck.middleware.model.VerificationRequest;
import com.pswied.osnabruck.middleware.service.BlockchainService;
import com.pswied.osnabruck.middleware.service.HashingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VerificationController.class)
class VerificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HashingService hashingService;

    @MockBean
    private BlockchainService blockchainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void verifyTransaction_shouldReturnVerified_whenHashesMatch() throws Exception {
        // Given
        VerificationRequest request = new VerificationRequest();
        request.setTraceId("trace123");
        request.setTransactionData(createSampleTransaction());

        AuditRecordDto mockRecord = new AuditRecordDto("trace123", "matchingHash", BigInteger.valueOf(1234567890L), "MOCK_SYSTEM");

        when(hashingService.calculateHash(any(TransactionRequest.class))).thenReturn("matchingHash");
        when(blockchainService.getAuditRecord("trace123")).thenReturn(mockRecord);

        // When & Then
        mockMvc.perform(post("/api/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("VERIFIED"))
                .andExpect(jsonPath("$.calculatedHash").value("matchingHash"))
                .andExpect(jsonPath("$.blockchainHash").value("matchingHash"));
    }

    @Test
    void verifyTransaction_shouldReturnTampered_whenHashesDoNotMatch() throws Exception {
        // Given
        VerificationRequest request = new VerificationRequest();
        request.setTraceId("trace123");
        request.setTransactionData(createSampleTransaction());

        AuditRecordDto mockRecord = new AuditRecordDto("trace123", "originalHash", BigInteger.valueOf(1234567890L), "MOCK_SYSTEM");

        when(hashingService.calculateHash(any(TransactionRequest.class))).thenReturn("tamperedHash");
        when(blockchainService.getAuditRecord("trace123")).thenReturn(mockRecord);

        // When & Then
        mockMvc.perform(post("/api/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("TAMPERED"))
                .andExpect(jsonPath("$.calculatedHash").value("tamperedHash"))
                .andExpect(jsonPath("$.blockchainHash").value("originalHash"));
    }

    private TransactionRequest createSampleTransaction() {
        TransactionRequest request = new TransactionRequest();
        request.setTransactionId("TX123");
        request.setSenderAccount("ACC1");
        request.setReceiverAccount("ACC2");
        request.setAmount(new BigDecimal("100.00"));
        request.setCurrency("USD");
        request.setTimestamp(Instant.now());
        return request;
    }
}
