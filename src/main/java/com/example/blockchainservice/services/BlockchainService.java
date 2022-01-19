package com.example.blockchainservice.services;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;

public interface BlockchainService {
     Blockchain addNewBlockToBlockchain(Blockchain blockchain,Block block);
     void minerBlock(String address);
     Block getLastBlock(String idBlockchain);
     boolean verifyBlockchain(Blockchain blockchain);
     double calculSolde(String address);

}
