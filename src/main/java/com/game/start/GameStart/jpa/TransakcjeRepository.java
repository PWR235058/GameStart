package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Osoby;
import com.game.start.GameStart.entity.Transakcje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransakcjeRepository
        extends JpaRepository<Transakcje, Long> {
}
