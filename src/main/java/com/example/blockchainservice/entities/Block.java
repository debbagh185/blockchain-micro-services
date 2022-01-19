
package com.example.blockchainservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date dateCreation;
    private String hashBlock;
    private String hashBlocPrec;
    @OneToMany(mappedBy = "block")
    private List<Transaction> listeTransactions=new ArrayList<>();
    private int nonce;
    @ManyToOne(fetch = FetchType.LAZY)
    private Blockchain blockchain;

    public Block(String hashBlocPrec, List<Transaction> listeTransactions, int nonce) {
        UUID uuid = UUID.randomUUID();
//        this.setId(uuid.toString());
        this.setDateCreation(new Date());
        this.hashBlocPrec = hashBlocPrec;
        this.listeTransactions = listeTransactions;
        this.nonce = nonce;
    }



}
