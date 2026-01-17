package com.pswied.osnabruck.middleware.model;

import java.math.BigInteger;

public class AuditRecordDto {
    private String traceId;
    private String auditHash;
    private BigInteger timestamp;
    private String sourceSystem;

    public AuditRecordDto(String traceId, String auditHash, BigInteger timestamp, String sourceSystem) {
        this.traceId = traceId;
        this.auditHash = auditHash;
        this.timestamp = timestamp;
        this.sourceSystem = sourceSystem;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getAuditHash() {
        return auditHash;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }
}
