package com.game.start.GameStart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pracownicy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String stanowisko;
    private int pensja;



    //private int idSklepu;


    //@OneToOne
    //private Osoby user;

}