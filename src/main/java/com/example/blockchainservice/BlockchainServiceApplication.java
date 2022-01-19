package com.example.blockchainservice;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.repositories.BlockRepository;
import com.example.blockchainservice.repositories.TransactionRepository;
import com.example.blockchainservice.services.BlockService;
import com.example.blockchainservice.services.BlockchainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BlockchainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockchainServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BlockService blockService, TransactionRepository transactionRepository){

        return  args -> {
            Block block1 =new Block("blockljsqfhkzedhskrjghsfi",null,1);
            blockService.saveBlock(block1);
        };
    }

}
