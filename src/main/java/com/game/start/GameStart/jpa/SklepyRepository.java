package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Osoby;
import com.game.start.GameStart.entity.Sklepy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SklepyRepository
        extends JpaRepository<Sklepy, Long> {
}
