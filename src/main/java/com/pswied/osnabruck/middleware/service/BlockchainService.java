package com.pswied.osnabruck.middleware.service;

import com.pswied.osnabruck.middleware.generated.AuditLedger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

import java.util.concurrent.CompletableFuture;

@Service
public class BlockchainService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final String contractAddress;

    public BlockchainService(Web3j web3j,
                             @Value("${blockchain.contract.address}") String contractAddress,
                             @Value("${blockchain.account.private-key}") String privateKey) {
        this.web3j = web3j;
        this.credentials = Credentials.create(privateKey);
        this.contractAddress = contractAddress;
    }

    public CompletableFuture<String> recordAuditAsync(String traceId, String auditHash, String sourceSystem) {
        AuditLedger contract = AuditLedger.load(contractAddress, web3j, credentials, new DefaultGasProvider());

        return contract.recordAudit(traceId, auditHash, sourceSystem).sendAsync()
                .thenApply(transactionReceipt -> {
                    // You can inspect the receipt here if needed
                    return transactionReceipt.getTransactionHash();
                });
    }
}
