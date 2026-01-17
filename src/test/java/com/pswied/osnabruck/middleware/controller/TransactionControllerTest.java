package com.pswied.osnabruck.middleware.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pswied.osnabruck.middleware.model.TransactionRequest;
import com.pswied.osnabruck.middleware.service.BlockchainService;
import com.pswied.osnabruck.middleware.service.HashingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HashingService hashingService;

    @MockBean
    private BlockchainService blockchainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void processTransaction_shouldReturnAccepted_whenValidRequest() throws Exception {
        // Given
        TransactionRequest request = new TransactionRequest();
        request.setTransactionId("TX123");
        request.setSenderAccount("ACC1");
        request.setReceiverAccount("ACC2");
        request.setAmount(new BigDecimal("100.00"));
        request.setCurrency("USD");
        request.setTimestamp(Instant.now());

        when(hashingService.calculateHash(any(TransactionRequest.class))).thenReturn("mockHash123");
        when(blockchainService.recordAuditAsync(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture("txHash123"));

        // When & Then
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("ACCEPTED"))
                .andExpect(jsonPath("$.auditHash").value("mockHash123"))
                .andExpect(jsonPath("$.traceId").exists());
    }

    @Test
    void processTransaction_shouldReturnBadRequest_whenInvalidRequest() throws Exception {
        // Given
        TransactionRequest request = new TransactionRequest();
        // Missing required fields

        // When & Then
        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
