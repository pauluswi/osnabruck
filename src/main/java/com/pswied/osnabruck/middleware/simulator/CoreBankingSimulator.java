package com.pswied.osnabruck.middleware.simulator;

import com.pswied.osnabruck.middleware.controller.TransactionController;
import com.pswied.osnabruck.middleware.model.TransactionRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Component
public class CoreBankingSimulator {

    private final TransactionController transactionController;
    private final Random random = new Random();

    public CoreBankingSimulator(TransactionController transactionController) {
        this.transactionController = transactionController;
    }

    // Run every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void simulateTransaction() {
        TransactionRequest request = generateRandomTransaction();
        
        System.out.println("\n[Simulator] Generating new transaction...");
        try {
            // In a real microservice, this would be an HTTP call. 
            // Here we call the controller directly for simplicity.
            transactionController.processTransaction(request);
        } catch (Exception e) {
            System.err.println("[Simulator] Failed to send transaction: " + e.getMessage());
        }
    }

    private TransactionRequest generateRandomTransaction() {
        TransactionRequest request = new TransactionRequest();
        request.setTransactionId("TX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        request.setSenderAccount("DE" + (10000000 + random.nextInt(90000000)));
        request.setReceiverAccount("DE" + (10000000 + random.nextInt(90000000)));
        request.setAmount(BigDecimal.valueOf(10 + random.nextInt(1000)));
        request.setCurrency("EUR");
        request.setTimestamp(Instant.now());
        return request;
    }
}
