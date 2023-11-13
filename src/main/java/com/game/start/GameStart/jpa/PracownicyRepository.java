package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Osoby;
import com.game.start.GameStart.entity.Pracownicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracownicyRepository
        extends JpaRepository<Pracownicy, Long> {
}
