package com.pswied.osnabruck.middleware.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class BlockchainConfig {

    @Value("${blockchain.node.url:http://localhost:8545}")
    private String blockchainNodeUrl;

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(blockchainNodeUrl));
    }
}
