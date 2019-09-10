package xyz.riocode.scoutpro.service;

import xyz.riocode.scoutpro.model.Player;

import java.util.Set;


public interface PlayerService {
    Player createOrUpdate(Player player, String username);
    Player addExistingPlayerToUser(Long id, boolean isUserPlayer, String username);
    Player getByIdAndUser(Long id, String username);
    Set<Player> getByNameAndUser(String playerName, String username);
    Set<Player> getByUserPaging(String username, int page);
    void delete(Long playerId, String username);
}
