package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {
}
