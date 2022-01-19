package com.example.blockchainservice.web;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.services.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/blockchain")
public class BlockchainRestApi {
    private BlockchainService blockchainService;

    public BlockchainRestApi(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @GetMapping(path = "/lastblockchain")
    public Block getLastBlock(@RequestBody String idBlockchain){
        return blockchainService.getLastBlock(idBlockchain);
    }

}
