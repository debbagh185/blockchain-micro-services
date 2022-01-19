package com.example.blockchainservice.repositories;

import com.example.blockchainservice.entities.Block;
import com.example.blockchainservice.entities.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BlockchainRepository  extends JpaRepository<Blockchain, String> {
}
