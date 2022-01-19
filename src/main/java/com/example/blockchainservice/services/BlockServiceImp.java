package com.example.blockchainservice.services;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.repositories.BlockRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class BlockServiceImp implements BlockService{
    private BlockRepository blockRepository;

        public BlockServiceImp(BlockRepository blockRepository) {
            this.blockRepository = blockRepository;
        }

    @Override
    public Block saveBlock(Block block) {
        String hash = calculateHashBloc(block);
        block.setHashBlock(hash);
        block.setDateCreation(new Date());
        System.out.println(block);
        return blockRepository.save(block);
    }


    @Override
    public String calculateHashBloc(Block block) {
        int hashCodeTransactions = block.getListeTransactions() != null ? block.getListeTransactions().hashCode() : 0;
        System.out.println(hashCodeTransactions+"========");
        String str = String.valueOf(block.getHashBlocPrec()+ block.getNonce()+ hashCodeTransactions);
        System.out.println(hashCodeTransactions);
        String hashStr = DigestUtils.sha256Hex(str);
        System.out.println(hashStr+"--------");
        String hash="";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
                    hashStr.getBytes(StandardCharsets.UTF_8));
            hash =  DatatypeConverter.printHexBinary(encodedhash);
            System.out.println(hash);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return hash;
    }

    @Override
    public void minerBlock(Block block) {
        String prefixString = new String(new char[block.getNonce()]).replace('\0', '0');
        while (!block.getHashBlock().substring(0, block.getNonce()).equals(prefixString)) {
            block.setNonce(block.getNonce()+1);
            block.setHashBlock(this.calculateHashBloc(block));
        }
    }
}

