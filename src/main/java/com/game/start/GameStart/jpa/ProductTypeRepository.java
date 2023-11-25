package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository
        extends JpaRepository<ProductType, Long> {
}
