package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Osoby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsobyRepository
        extends JpaRepository<Osoby, Long> {
    Osoby findByLogin(String login);
    Osoby findByEmail(String email);
}
