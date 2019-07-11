package xyz.riocode.scoutpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.scoutpro.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
