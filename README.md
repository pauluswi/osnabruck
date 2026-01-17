# Osnabrück - Immutable Audit Ledger

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green?style=for-the-badge&logo=spring-boot)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?style=for-the-badge&logo=apache-maven)
![Blockchain](https://img.shields.io/badge/Blockchain-Ledger-blueviolet?style=for-the-badge&logo=blockchain)
![Coverage](https://img.shields.io/badge/Coverage-76%25-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**Blockchain-Backed Audit Trail for Financial Middleware**

## Overview

**Immutable Audit Ledger** is a showcase project demonstrating how blockchain concepts can be used as a **cryptographic audit and integrity layer** for financial middleware systems.

Instead of storing sensitive transaction data on-chain, this project records **cryptographic hashes of transaction events** into a **ledger**, enabling:

- Tamper-evident audit logs
- Cryptographic proof of integrity
- End-to-end traceability
- Regulatory-ready audit verification

The design reflects **enterprise and financial-sector constraints**, emphasizing compliance, reproducibility, and security-by-design.

---

## Architecture Overview

    ┌─────────────────────┐
    │ Mock Core Banking   │
    │ (Internal Simulator)│
    └─────────┬───────────┘
              │ JSON Transaction (Every 10s)
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
    │ Audit Ledger               │
    │ - In-Memory Mock Blockchain│
    │ - Immutable Record Storage │
    └─────────┬──────────────────┘
              │ Verification Query
              ▼
    ┌────────────────────────────┐
    │ Audit Verifier Service     │
    │ (Module inside Middleware) │
    │ - Re-hash payload          │
    │ - Compare with Ledger      │
    │ - Integrity status         │
    └────────────────────────────┘

---

## Key Features

1.  **Transaction Middleware**: A Spring Boot application that validates and processes financial transactions.
2.  **Cryptographic Hashing**: Uses SHA-256 to create a unique fingerprint of every transaction.
3.  **Immutable Ledger**: Stores the hash and trace ID in a tamper-evident store (currently simulated in-memory for ease of demonstration).
4.  **Audit Verification**: An API endpoint that allows auditors to verify if a transaction record has been altered by comparing its re-calculated hash against the immutable ledger.
5.  **Traffic Simulator**: An internal component that automatically generates random banking transactions to populate the system.

---

## Technology Stack

| Layer | Technology |
|-----|-----------|
| Backend | Java 17, Spring Boot 3.2 |
| Build Tool | Maven |
| API Docs | Swagger / OpenAPI |
| Hashing | SHA-256 |
| Ledger | In-Memory Mock (Simulating Private Blockchain) |
| Testing | JUnit 5, Mockito |

---

## Real-World Blockchain Integration

In this demonstration, the **Blockchain Layer** is mocked using an in-memory `ConcurrentHashMap` to simplify setup and testing.

**In a production environment**, this mock would be replaced by a real blockchain integration:

1.  **Smart Contract**: A Solidity contract (e.g., `AuditLedger.sol`) would be deployed to a private Ethereum network (e.g., Hyperledger Besu, Quorum, or a private Geth node).
    *   *Note: A reference implementation of this contract is included in `blockchain/AuditLedger.sol`.*
2.  **Web3j Integration**: The `BlockchainService` would use the **Web3j** library to sign and send transactions to the smart contract.
3.  **Immutability**: Once mined, the audit hash is cryptographically secured by the blockchain network, making it impossible to alter or delete without invalidating the entire chain.
4.  **Decentralization**: Multiple nodes (e.g., Regulator Node, Bank Node, Auditor Node) would host the ledger, ensuring no single party controls the audit trail.

This project demonstrates the **application logic** and **verification flow** that remains identical regardless of the underlying storage mechanism.

---

## Getting Started

### Prerequisites
- Java 17+
- Maven

### Running the Application

1.  **Clone the repository**
    ```bash
    git clone https://github.com/your-repo/osnabruck.git
    cd osnabruck
    ```

2.  **Run the Application**
    You can run it directly using Maven or your IDE.
    ```bash
    mvn spring-boot:run
    ```
    *The application will start on port 8080.*

3.  **Watch the Simulator**
    Check the console logs. You will see the **Mock Core Banking Simulator** generating transactions every 10 seconds:
    ```text
    [Simulator] Generating new transaction...
    Processing Transaction: TX-1A2B3C4D
    MOCK: Audit recorded. Trace ID: ...
    ```

---

## Usage & API Documentation

Once the application is running, you can explore the API via the **Swagger UI**:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### 1. Submit a Transaction (Manual)
**POST** `/api/transactions`
```json
{
  "transactionId": "TX-1001",
  "senderAccount": "DE123456789",
  "receiverAccount": "DE987654321",
  "amount": 500.00,
  "currency": "EUR",
  "timestamp": "2023-10-27T10:00:00Z"
}
```
*Response includes a `traceId`.*

### 2. Verify Integrity (Audit)
**POST** `/api/verify`
Use the `traceId` from the previous step.
```json
{
  "traceId": "YOUR_TRACE_ID",
  "transactionData": { ...same data as above... }
}
```
*Response: `VERIFIED`*

### 3. Simulate Tampering
Try verifying with a modified amount (e.g., change 500.00 to 500.01).
*Response: `TAMPERED`*

---

## Testing

Run the automated test suite:
```bash
mvn test
```

Or run the provided shell script to test the API flow:
```bash
./test_api.sh
```

---

## Project Structure

The project is a monolithic Spring Boot application organized by feature modules:

- **Transaction Processing**:
  - `TransactionController`: Receives and validates incoming requests.
  - `HashingService`: Generates SHA-256 hashes.
  - `BlockchainService`: Writes the hash to the immutable ledger.

- **Audit Verification (The "Verifier Service")**:
  - `VerificationController`: Exposes the `/api/verify` endpoint.
  - Re-uses `HashingService` and `BlockchainService` (read-only) to validate integrity.

- **Simulation**:
  - `CoreBankingSimulator`: Generates random traffic.

- **Data Models**:
  - `TransactionRequest`: The financial payload.
  - `AuditRecordDto`: The on-chain record structure.

---

## Disclaimer

This project is a technical showcase. It does not represent a production banking system and contains no real customer or financial data. The "Blockchain" layer is currently mocked in-memory to simplify local development and demonstration.
