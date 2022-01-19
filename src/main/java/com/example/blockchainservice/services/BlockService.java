package com.example.blockchainservice.services;

import com.example.blockchainservice.entities.Block;

public interface BlockService {
    Block saveBlock(Block block);
    String calculateHashBloc(Block block);
    void minerBlock(Block block);
}
