package com.example.blockchainservice.services;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Transaction;
import com.example.blockchainservice.repositories.BlockRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class BlockServiceImpl implements BlockService {

    private BlockRepository blockRepository;

    Logger logger = LoggerFactory.getLogger(BlockServiceImpl.class);

    public BlockServiceImpl(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    @Override
    public Block newBlock(String prevHash) {
        Block block = new Block();
        block.setId(UUID.randomUUID().toString());
        block.setDateCreation(new Date());
        block.setHashBlockPrev(prevHash);
        block.setHashBlock(calculateHash(block));
        return blockRepository.save(block);
    }

    @Override
    public String calculateHash(Block block) {
        int hashCodeTransactions = block.getListeTransactions() != null ? block.getListeTransactions().hashCode() : 0;
        System.out.println(hashCodeTransactions+"========");
        String str = String.valueOf(block.getHashBlockPrev()+ block.getNonce()+ hashCodeTransactions);
        System.out.println(hashCodeTransactions);
        String hashStr = DigestUtils.sha256Hex(str);
        System.out.println(hashStr+"--------");
        String hash="";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    hashStr.getBytes(StandardCharsets.UTF_8));
            hash = DatatypeConverter.printHexBinary(encodedHash);
            System.out.println(hash);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return hash;
    }

    @Override
    public void mineBlock(Block block, int difficulty) {
        String prefix = new String(new char[difficulty]).replace('\0', '0');
        while (!block.getHashBlock().substring(0, difficulty).equals(prefix)) {
            block.setNonce(block.getNonce()+1);
            block.setHashBlock(calculateHash(block));
        }
        blockRepository.save(block);
        logger.info("Block mined : "+block.getHashBlock());
    }
}
