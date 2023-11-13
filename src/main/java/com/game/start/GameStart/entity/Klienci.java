package com.game.start.GameStart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Klienci {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long KlientId;

    private String miejscowosc;
    private String ulica;
    private String numerDomu;

    //@OneToOne
    //private Osoby user;

}
