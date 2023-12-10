package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.ProductType;
import com.game.start.GameStart.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository
        extends JpaRepository<Seller, Long> {
}
