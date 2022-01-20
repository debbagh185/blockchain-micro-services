package com.example.blockchainservice.web;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import com.example.blockchainservice.repositories.BlockchainRepository;
import com.example.blockchainservice.services.BlockchainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/blockchain")
public class BlockchainController {
    private BlockchainRepository blockchainRepository;

    public BlockchainController(BlockchainRepository blockchainRepository) {
        this.blockchainRepository = blockchainRepository;
    }

    @GetMapping("/getAll")
    public Blockchain displayChain() {
        List<Blockchain> blockchainList = blockchainRepository.findAll();
        if (blockchainList.size() > 0)
            return blockchainList.get(0);
        return null;
    }
}
