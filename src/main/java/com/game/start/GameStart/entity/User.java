package com.game.start.GameStart.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNullFields;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    private String login ;//todo @unique but i don't know how
    private String email ;//@unique?
    @JsonIgnore
    private String password  ;
    @JsonIgnore
    private byte[] salt;

    @OneToOne
    private Client client;
    @OneToOne
    private Worker worker;


}
