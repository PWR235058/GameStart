package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Client;
import com.game.start.GameStart.entity.ProductType;
import com.game.start.GameStart.entity.Seller;
import com.game.start.GameStart.entity.Shop;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository
        extends JpaRepository<Seller, Long> {
    Seller findByClient(Client client);
    Seller findByShop(Shop shop);
}
