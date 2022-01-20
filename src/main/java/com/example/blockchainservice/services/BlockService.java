package com.example.blockchainservice.services;

import com.example.blockchainservice.entities.Block;

public interface BlockService {
    Block newBlock(String prevHash);
    String calculateHash(Block block);
    void mineBlock(Block block, int difficulty);
}
