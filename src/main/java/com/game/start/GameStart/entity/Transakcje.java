package com.game.start.GameStart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transakcje {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Klienci klient;
    @ManyToOne
    private Produkty produkt;

    private String rodzajAkcji;//wymiana/kupno/wyporzyczyć
    private float koszt;
}
