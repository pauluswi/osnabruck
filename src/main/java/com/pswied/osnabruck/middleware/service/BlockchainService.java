package com.pswied.osnabruck.middleware.service;

import com.pswied.osnabruck.middleware.model.AuditRecordDto;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MOCK IMPLEMENTATION
 * Stores audit records in memory instead of a real blockchain.
 * Useful for testing logic without infrastructure dependencies.
 */
@Service
public class BlockchainService {

    // In-memory storage to simulate the ledger
    private final Map<String, AuditRecordDto> ledger = new ConcurrentHashMap<>();

    public BlockchainService() {
        System.out.println("BlockchainService initialized in MOCK MODE (In-Memory).");
    }

    public CompletableFuture<String> recordAuditAsync(String traceId, String auditHash, String sourceSystem) {
        // Simulate blockchain processing time
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100); // Simulate network latency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (ledger.containsKey(traceId)) {
                throw new RuntimeException("Audit record for this Trace ID already exists");
            }

            AuditRecordDto record = new AuditRecordDto(
                    traceId,
                    auditHash,
                    BigInteger.valueOf(Instant.now().getEpochSecond()),
                    sourceSystem
            );

            ledger.put(traceId, record);
            
            String mockTxHash = "0xMOCK" + Integer.toHexString(record.hashCode());
            System.out.println("MOCK: Audit recorded. Trace ID: " + traceId + ", Tx Hash: " + mockTxHash);
            
            return mockTxHash;
        });
    }

    public AuditRecordDto getAuditRecord(String traceId) throws Exception {
        if (!ledger.containsKey(traceId)) {
            throw new RuntimeException("Record not found for Trace ID: " + traceId);
        }
        return ledger.get(traceId);
    }
}
