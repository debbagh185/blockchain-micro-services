package com.example.blockchainservice;

import com.example.blockchainservice.repositories.TransactionRepository;
import com.example.blockchainservice.services.BlockchainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlockchainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BlockchainService blockchainService, TransactionRepository transactionRepository){

        return args -> {
            try {
                blockchainService.newChain(1, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

}
