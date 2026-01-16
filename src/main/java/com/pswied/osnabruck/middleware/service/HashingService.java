package com.pswied.osnabruck.middleware.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pswied.osnabruck.middleware.model.TransactionRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Service
public class HashingService {

    private final ObjectMapper objectMapper;

    public HashingService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true); // Ensure deterministic JSON
    }

    public String calculateHash(TransactionRequest request) {
        try {
            // 1. Canonicalize the payload (deterministic JSON)
            String jsonPayload = objectMapper.writeValueAsString(request);

            // 2. Compute SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(jsonPayload.getBytes(StandardCharsets.UTF_8));

            // 3. Convert to Hex string
            return HexFormat.of().formatHex(encodedhash);

        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Error calculating hash for transaction", e);
        }
    }
}
