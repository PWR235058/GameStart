package com.game.start.GameStart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sklepy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String miejscowosc;
    private String ulica;
    private String nrDomu;

    @ManyToMany
    private List<Pracownicy> pracownicy;
}
