package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Klienci;
import com.game.start.GameStart.entity.Osoby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KlienciRepository
        extends JpaRepository<Klienci, Long> {

}
