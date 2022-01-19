package com.example.blockchainservice.web;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.services.BlockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/block")
public class BlockRestApi {
    private BlockService blockService;

    public BlockRestApi(BlockService blockService) {
        this.blockService = blockService;
    }

    @PostMapping(path = "/newblock")
    public Block addNewBlock(Block block){
        return blockService.saveBlock(block);
    }
}
