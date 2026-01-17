package com.pswied.osnabruck.middleware.controller;

import com.pswied.osnabruck.middleware.model.AuditRecordDto;
import com.pswied.osnabruck.middleware.model.VerificationRequest;
import com.pswied.osnabruck.middleware.service.BlockchainService;
import com.pswied.osnabruck.middleware.service.HashingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/verify")
public class VerificationController {

    private final HashingService hashingService;
    private final BlockchainService blockchainService;

    public VerificationController(HashingService hashingService, BlockchainService blockchainService) {
        this.hashingService = hashingService;
        this.blockchainService = blockchainService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> verifyTransaction(@Valid @RequestBody VerificationRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 1. Re-calculate Hash
            String calculatedHash = hashingService.calculateHash(request.getTransactionData());

            // 2. Fetch from Blockchain
            AuditRecordDto auditRecord = blockchainService.getAuditRecord(request.getTraceId());

            // 3. Compare
            boolean isMatch = calculatedHash.equals(auditRecord.getAuditHash());

            response.put("status", isMatch ? "VERIFIED" : "TAMPERED");
            response.put("traceId", request.getTraceId());
            response.put("calculatedHash", calculatedHash);
            response.put("blockchainHash", auditRecord.getAuditHash());
            response.put("blockchainTimestamp", auditRecord.getTimestamp());
            
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("status", "ERROR");
            response.put("message", "Verification failed: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
