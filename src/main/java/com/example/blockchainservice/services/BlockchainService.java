package com.example.blockchainservice.services;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Transaction;

import java.util.List;

public interface BlockchainService {
    Blockchain newChain(int difficulty, int miningReward);
    Block addBlock(Blockchain chain, String miner);
    Transaction addTransaction(Transaction transaction);
    Block getLastBlock(String blockchainId);
    boolean isChainValid(Blockchain chain);
    Transaction findTransaction(Blockchain chain, String transactionId);
    List<Transaction> getAllTransactions(Blockchain chain);
}
