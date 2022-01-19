package com.example.blockchainservice.services;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.repositories.BlockchainRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlockchainServiceImp implements BlockchainService{
    BlockchainRepository blockchainRepository;

    public BlockchainServiceImp(BlockchainRepository blockchainRepository) {
        this.blockchainRepository = blockchainRepository;
    }

    @Override
    public Blockchain addNewBlockToBlockchain(Blockchain blockchain,Block block) {
        blockchain.getListBlocks().add(block);
        return blockchain;
    }

    @Override
    public void minerBlock(String address) {

    }

    @Override
    public Block getLastBlock(String idBlockchain) {
        Blockchain blockchain = blockchainRepository.getById(idBlockchain);
        List<Block> list_Blocks = blockchain.getListBlocks();
        return  list_Blocks.get(list_Blocks.size()-1);
    }

    @Override
    public boolean verifyBlockchain(Blockchain blockchain) {
        return false;
    }

    @Override
    public double calculSolde(String address) {
        return 0;
    }
}
