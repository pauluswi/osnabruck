

# Osnabrück - Immutable Audit Ledger
**Blockchain-Backed Audit Trail for Financial Middleware**

## Overview

**Immutable Audit Ledger** is a showcase project demonstrating how blockchain can be used as a **cryptographic audit and integrity layer** for financial middleware systems.

Instead of storing sensitive transaction data on-chain, this project records **cryptographic hashes of transaction events** into a **private blockchain**, enabling:

- Tamper-evident audit logs
- Cryptographic proof of integrity
- End-to-end traceability
- Regulatory-ready audit verification

The design reflects **enterprise and financial-sector constraints**, emphasizing compliance, reproducibility, and security-by-design rather than speculative crypto use cases.

---

## Problem Statement

Financial systems must ensure that:
- Transaction logs cannot be altered without detection
- Audit trails remain verifiable years later
- Integrity can be proven independently of the application database

Traditional database logs alone **do not provide cryptographic immutability**.

---

## Solution Approach

This project introduces a **Blockchain-Backed Audit Ledger**:

1. Transaction events are processed by middleware
2. A deterministic **hash of the event** is generated
3. The hash and metadata are written to a **private blockchain**
4. Actual business data remains **off-chain**
5. Auditors can later verify integrity by re-hashing and comparing on-chain records

---

## Key Design Principles

- **Blockchain as Proof, Not Storage**
- **Off-Chain Data, On-Chain Integrity**
- **Explicit Verification (Zero-Trust Mindset)**
- **Auditability by Design**
- **Reproducible Local Environment**

---

## Architecture Overview

    ┌─────────────────────┐
    │ Mock Core Banking   │
    │ (Simulated System)  │
    └─────────┬───────────┘
              │ JSON Transaction
              ▼
    ┌────────────────────────────┐
    │ Transaction Middleware     │
    │ - Validation               │
    │ - Trace ID generation      │
    │ - Hashing (SHA-256)        │
    │ - Audit event creation     │
    └─────────┬──────────────────┘
              │ Hash + Metadata
              ▼
    ┌────────────────────────────┐
    │ Private Blockchain Network │
    │ - Smart Contract           │
    │ - Immutable Ledger         │
    └─────────┬──────────────────┘
              │ Verification Query
              ▼
    ┌────────────────────────────┐
    │ Audit Verifier Service     │
    │ - Re-hash payload          │
    │ - Compare with blockchain  │
    │ - Integrity status         │
    └────────────────────────────┘


---

## Components

### 1. Mock Core Banking Service
- Simulates upstream enterprise systems
- Provides deterministic JSON payloads
- No sensitive or real banking data

> External systems are intentionally mocked to represent real-world integration patterns without licensing or data exposure.

---

### 2. Transaction Middleware (Core Component)
- Java Spring Boot
- Request validation
- Trace ID propagation
- SHA-256 hashing of canonical payload
- Blockchain interaction
- Structured audit logging

This component represents **production-grade middleware design**.

---

### 3. Audit Ledger Smart Contract
- Solidity
- Stores:
    - Event hash
    - Timestamp
    - Source system ID
    - Trace ID
- Append-only design

No business data is stored on-chain.

---

### 4. Audit Verifier Service
- Recomputes event hash
- Retrieves on-chain record
- Verifies integrity
- Reports tamper status

Designed to simulate **auditor or regulator verification workflows**.

---

## Technology Stack

| Layer | Technology |
|-----|-----------|
| Backend | Java 17, Spring Boot |
| Blockchain | Ethereum Private Network |
| Smart Contracts | Solidity |
| Hashing | SHA-256 |
| Containerization | Docker, Docker Compose |
| Architecture Style | Microservices |
| Security | Zero-Trust principles |

---

## Security & Compliance Considerations

- TLS for all service communication
- No sensitive data stored on-chain
- Deterministic hashing for reproducibility
- Immutable audit records
- Traceable request lifecycle
- Designed to meet **financial regulatory audit requirements**

---

## Why Blockchain Is Used Here

Blockchain is **not** used for payments or tokens.

It is used as:
- A **cryptographic integrity anchor**
- An **independent audit layer**
- A **tamper-evident ledger**

This aligns with enterprise and regulatory expectations.

---

## Local Setup (High Level)

```bash
docker-compose up
```

This starts:

    Mock core banking service
    
    Middleware service
    
    Private blockchain node
    
    Audit verifier service
    
    All components run locally for full reproducibility.

## Intended Audience

    Financial institutions
    Banking middleware teams
    Auditors & compliance stakeholders
    Engineers designing regulated systems
    Architecture and security reviewers

## What This Project Demonstrates

    ✔ Enterprise blockchain design
    ✔ Auditability by design
    ✔ Cryptographic integrity proof
    ✔ Middleware architecture expertise
    ✔ Compliance-aware engineering

## Disclaimer

This project is a technical showcase.
It does not represent a production banking system and contains no real customer or financial data.