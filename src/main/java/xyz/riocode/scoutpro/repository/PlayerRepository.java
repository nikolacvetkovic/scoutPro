package xyz.riocode.scoutpro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.riocode.scoutpro.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query(value = "SELECT p FROM Player p " +
                " LEFT JOIN FETCH p.characteristic " +
                " JOIN FETCH p.pesDbInfo " +
                " JOIN FETCH p.transfermarktInfo " +
                " JOIN FETCH p.psmlInfo psml " +
                " LEFT JOIN FETCH psml.psmlTransfers " +
                " LEFT JOIN FETCH p.marketValues " +
                " LEFT JOIN FETCH p.competitionStatistics " +
                " JOIN FETCH p.users up " +
                " JOIN FETCH up.appUser u " +
                " WHERE u.username = :username",
            countQuery = "SELECT count(p) FROM Player p JOIN p.users up JOIN up.appUser u WHERE u.username = :username")
    Page<Player> findPlayersByUsername(String username, Pageable pageable);

    @Query("SELECT p FROM Player p JOIN FETCH p.users up JOIN FETCH up.appUser u WHERE p.id = :id AND u.username = :username")
    Optional<Player> findPlayerByIdAndUsername(Long id, String username);

    @Query("SELECT p FROM Player p " +
            " JOIN FETCH p.characteristic " +
            " JOIN FETCH p.pesDbInfo " +
            " JOIN FETCH p.transfermarktInfo "+
            " JOIN FETCH p.psmlInfo psml "+
            " LEFT JOIN FETCH psml.psmlTransfers " +
            " LEFT JOIN FETCH p.transfers " +
            " LEFT JOIN FETCH p.marketValues " +
            " LEFT JOIN FETCH p.competitionStatistics " +
            " LEFT JOIN FETCH p.positionStatistics " +
            " LEFT JOIN FETCH p.gameStatistics " +
            " JOIN FETCH p.users up " +
            " JOIN FETCH up.appUser u " +
            " WHERE p.id = :id" +
            " AND u.username = :username ")
    Optional<Player> findPlayerByIdAndUsernameComplete(Long id, String username);

    @Query("SELECT p FROM Player p " +
            "JOIN FETCH p.characteristic " +
            "JOIN FETCH p.pesDbInfo " +
            "JOIN FETCH p.transfermarktInfo "+
            "JOIN FETCH p.psmlInfo psml "+
            "JOIN FETCH p.users up " +
            "JOIN FETCH up.appUser u " +
            "WHERE p.playerName LIKE LOWER(CONCAT('%', :playerName, '%')) " +
            "AND u.username = :username")
    List<Player> findByPlayerNameAndUsername(String playerName, String username);

    Player findByTransfermarktUrl(String transfermarktUrl);
    @Query("SELECT p FROM Player p JOIN FETCH p.users up JOIN FETCH up.appUser u JOIN FETCH p.pesDbInfo pes WHERE pes.playerName = :pesDbName AND u.username = :username")
    Player findByPesDbName(String pesDbName, String username);
}
