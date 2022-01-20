package com.example.blockchainservice.web;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.repositories.BlockchainRepository;
import com.example.blockchainservice.services.BlockService;
import com.example.blockchainservice.services.BlockchainService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/block")
public class BlockController {
    private BlockchainService blockchainService;
    private BlockchainRepository blockchainRepository;

    public BlockController(BlockchainService blockchainService, BlockchainRepository blockchainRepository) {
        this.blockchainService = blockchainService;
        this.blockchainRepository = blockchainRepository;
    }

    @GetMapping("/mine")
    public Block mine(@RequestParam String miner) {
        Block block = blockchainService.addBlock(blockchainRepository.findAll().get(0), miner);
        return block;
    }

}
