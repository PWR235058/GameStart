package com.game.start.GameStart.jpa;

import com.game.start.GameStart.entity.DataCarrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCarrierRepository
        extends JpaRepository<DataCarrier, Long> {
}
