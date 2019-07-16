package xyz.riocode.scoutpro.service;

import xyz.riocode.scoutpro.model.Player;

import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerService {
    Player create(Player player, String username);
    Player getByIdAndUser(Long id, String username);
    Set<Player> getByNameAndUser(String playerName, String username);
    Set<Player> getByUserPaging(String username, int page);
    Player update(Player player, String username);
    void delete(Long playerId, String username);
}
