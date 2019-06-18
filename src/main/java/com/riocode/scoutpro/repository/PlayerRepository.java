package com.riocode.scoutpro.repository;

import com.riocode.scoutpro.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
