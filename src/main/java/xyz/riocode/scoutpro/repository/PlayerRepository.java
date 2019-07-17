package xyz.riocode.scoutpro.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.riocode.scoutpro.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p JOIN FETCH p.users up JOIN FETCH up.appUser u WHERE u.username = :username")
    List<Player> findPlayersByUsername(String username, Pageable pageable);

    @Query("SELECT p FROM Player p JOIN FETCH p.users up JOIN FETCH up.appUser u WHERE p.id = :id AND u.username = :username")
    Optional<Player> findPlayerByIdAndUsername(Long id, String username);

    @Query("SELECT p FROM Player p JOIN FETCH p.users up JOIN FETCH up.appUser u WHERE p.playerName = :playerName AND u.username = :username")
    List<Player> findByPlayerNameAndUsername(String playerName, String username);
}
