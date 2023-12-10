package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Shop;
import com.game.start.GameStart.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository
        extends JpaRepository<Shop, Long> {
    Shop findByWorkersContains(Worker worker);
}
