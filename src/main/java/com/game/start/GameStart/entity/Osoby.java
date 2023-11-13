package com.game.start.GameStart.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Osoby {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId  ;

    private String imie;
    private String nazwisko;

    private String login ;
    @JsonIgnore
    private String password  ;
    @JsonIgnore
    private byte[] salt;
    private String email ;

    @OneToOne
    private Klienci klient;//nullable
    @OneToOne
    private Pracownicy pracownik;


}
