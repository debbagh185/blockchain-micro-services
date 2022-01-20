package com.example.blockchainservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String addressSrc;
    private String addressDest;
    private Date date ;
    private double amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Block block;

    public Transaction(String addressSrc, String addressDest, double amount) {
        UUID uuid = UUID.randomUUID();
        this.setId(uuid.toString());
        this.setAddressSrc(addressSrc);
        this.setAddressDest(addressDest);
        this.setAmount(amount);
        this.setDate(new Date());
    }
}
