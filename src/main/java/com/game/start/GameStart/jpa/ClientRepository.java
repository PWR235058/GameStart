package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository
        extends JpaRepository<Client, Long> {

}
