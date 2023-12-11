package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<Product, Long> {
    List<Product> findAllByStockGreaterThan(float x);
}
