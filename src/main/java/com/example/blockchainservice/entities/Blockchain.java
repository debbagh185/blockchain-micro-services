package com.example.blockchainservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blockchain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nom;
    private int difficulte;
    private double miningReward;
    @OneToMany(mappedBy = "blockchain")
    private List<Block> listBlocks;

}
