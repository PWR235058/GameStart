package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository
        extends JpaRepository<Worker, Long> {
}
