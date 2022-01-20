package com.example.blockchainservice.web;

import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.repositories.TransactionRepository;
import com.example.blockchainservice.services.BlockchainService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    private BlockchainService blockchainService;
    private TransactionRepository transactionRepository;

    public TransactionController(BlockchainService blockchainService, TransactionRepository transactionRepository) {
        this.blockchainService = blockchainService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/create")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        transaction = new Transaction(transaction.getAddressSrc(),
                transaction.getAddressDest(),
                transaction.getAmount());
        blockchainService.addTransaction(transaction);
        return transactionRepository.save(transaction);
    }

}
