package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Client;
import com.game.start.GameStart.entity.Seller;
import com.game.start.GameStart.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {
    List<Transaction> findByClient(Client c);

    List<Transaction> findAllByProduct_Seller(Seller seller);
}
