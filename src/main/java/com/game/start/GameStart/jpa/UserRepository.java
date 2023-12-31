package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
    User findByLogin(String login);
    User findByEmail(String email);
}
