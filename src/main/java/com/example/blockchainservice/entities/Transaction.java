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
    private String adress_source;
    private String adress_dest;
    private Date date ;
    private double montant;
    @ManyToOne(fetch = FetchType.LAZY)
    private Block block;

    public Transaction(String adress_source, String adress_dest, double montant) {
        UUID uuid = UUID.randomUUID();
        this.setId(uuid.toString());
        this.adress_source = adress_source;
        this.setAdress_dest(adress_dest);
        this.setMontant(montant);
        this.setDate(new Date());
    }
}
