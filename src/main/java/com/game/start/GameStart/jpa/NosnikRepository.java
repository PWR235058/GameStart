package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Nosnik;
import com.game.start.GameStart.entity.Osoby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NosnikRepository
        extends JpaRepository<Nosnik, Long> {
}
