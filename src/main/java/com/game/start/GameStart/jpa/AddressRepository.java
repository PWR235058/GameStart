package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository
        extends JpaRepository<Address, Long> {
}
