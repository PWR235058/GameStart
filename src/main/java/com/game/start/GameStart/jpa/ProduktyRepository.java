package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Osoby;
import com.game.start.GameStart.entity.Produkty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktyRepository
        extends JpaRepository<Produkty, Long> {
}
