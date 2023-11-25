package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SessionRepository
        extends JpaRepository<Session, Long> {
    Session findByToken(String token);
    @Transactional
    void removeByToken(String token);
}
