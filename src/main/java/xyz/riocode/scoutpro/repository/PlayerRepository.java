package xyz.riocode.scoutpro.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.riocode.scoutpro.model.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p JOIN FETCH p.users up JOIN FETCH up.appUser WHERE up.appUser.username = :username")
    List<Player> findByUsername(String username, Pageable pageable);
}
