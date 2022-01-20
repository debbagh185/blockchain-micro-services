package com.example.blockchainservice.services;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.repositories.BlockRepository;
import com.example.blockchainservice.repositories.BlockchainRepository;
import com.example.blockchainservice.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BlockchainServiceImpl implements BlockchainService {
    private BlockService blockService;
    private BlockRepository blockRepository;
    private BlockchainRepository blockchainRepository;
    private TransactionRepository transactionRepository;

    public BlockchainServiceImpl(BlockService blockService, BlockRepository blockRepository,
                                 BlockchainRepository blockchainRepository, TransactionRepository transactionRepository) {
        this.blockService = blockService;
        this.blockRepository = blockRepository;
        this.blockchainRepository = blockchainRepository;
        this.transactionRepository = transactionRepository;
    }

    private final List<Transaction> pendingTransactions = new ArrayList<>();

    @Override
    public Blockchain newChain(int difficulty, int miningReward) {
        Blockchain chain = new Blockchain();
        chain.setId(UUID.randomUUID().toString());
        chain.setNom("Blockchain");
        chain.setDifficulty(difficulty);
        chain.setMiningReward(miningReward);
        List<Block> blocks = new ArrayList<>();
        Block genesisBlock = blockService.newBlock("0");
        blocks.add(genesisBlock);

        chain.setListBlocks(blocks);
        return blockchainRepository.save(chain);
    }

    @Override
    public Block addBlock(Blockchain chain, String miner) {
        Transaction bonus = new Transaction("0", miner, chain.getMiningReward());
        pendingTransactions.add(bonus);
        transactionRepository.save(bonus);
        Block lastBlock = getLastBlock(chain.getId());
        String previousHash = lastBlock.getHashBlock();
        Block newBlock = blockService.newBlock(previousHash);
        newBlock.setListeTransactions(new ArrayList<>(pendingTransactions));
        blockService.mineBlock(newBlock, chain.getDifficulty());
        pendingTransactions.clear();
        chain.getListBlocks().add(newBlock);
        blockRepository.save(newBlock);
        blockchainRepository.save(chain);

        return newBlock;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
        return transaction;
    }

    @Override
    public Block getLastBlock(String idBlockchain) {
        Blockchain blockchain = blockchainRepository.getById(idBlockchain);
        List<Block> list_Blocks = blockchain.getListBlocks();
        return  list_Blocks.get(list_Blocks.size()-1);
    }

    @Override
    public boolean isChainValid(Blockchain chain) {
        boolean isValid = true;
        List<Block> blocks = chain.getListBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            String previousHash = i == 0 ? "0" : blocks.get(i - 1).getHashBlock();
            isValid = blocks.get(i).getHashBlock().equals(blockService.calculateHash(blocks.get(i)))
                    && previousHash.equals(blocks.get(i).getHashBlock());
            if (!isValid)
                break;
        }
        return isValid;
    }

    @Override
    public Transaction findTransaction(Blockchain chain, String transactionId) {
        for(Block block : chain.getListBlocks()) {
            for(Transaction tx : block.getListeTransactions()) {
                if(tx.getId().equals(transactionId)) return tx;
            }
        }
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions(Blockchain chain) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        for(Block block : chain.getListBlocks()) {
            transactions.addAll(block.getListeTransactions());
        }
        return transactions;
    }


}
