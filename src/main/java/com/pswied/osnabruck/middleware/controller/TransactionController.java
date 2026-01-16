package com.pswied.osnabruck.middleware.controller;

import com.pswied.osnabruck.middleware.model.TransactionRequest;
import com.pswied.osnabruck.middleware.service.BlockchainService;
import com.pswied.osnabruck.middleware.service.HashingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final HashingService hashingService;
    private final BlockchainService blockchainService;

    public TransactionController(HashingService hashingService, BlockchainService blockchainService) {
        this.hashingService = hashingService;
        this.blockchainService = blockchainService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> processTransaction(@Valid @RequestBody TransactionRequest request) {
        // 1. Generate Trace ID
        String traceId = UUID.randomUUID().toString();

        // 2. Calculate Hash
        String auditHash = hashingService.calculateHash(request);

        // 3. Submit to Blockchain (Async)
        blockchainService.recordAuditAsync(traceId, auditHash, "MOCK_CORE_BANKING")
                .thenAccept(txHash -> {
                    System.out.println("Audit recorded on blockchain. Tx Hash: " + txHash);
                })
                .exceptionally(ex -> {
                    System.err.println("Failed to record audit on blockchain: " + ex.getMessage());
                    return null;
                });

        System.out.println("Processing Transaction: " + request.getTransactionId());
        System.out.println("Trace ID: " + traceId);
        System.out.println("Audit Hash: " + auditHash);

        // 4. Return response
        return ResponseEntity.ok(Map.of(
                "status", "ACCEPTED",
                "traceId", traceId,
                "auditHash", auditHash
        ));
    }
}
