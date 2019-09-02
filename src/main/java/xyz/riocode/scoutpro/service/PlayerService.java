package xyz.riocode.scoutpro.service;

import xyz.riocode.scoutpro.model.Player;

import java.util.Set;


public interface PlayerService {
    Player createAndAddToUser(Player player, String username);
    //Player addToUser(List<Long> id, String username);
    Player getByIdAndUser(Long id, String username);
    Set<Player> getByNameAndUser(String playerName, String username);
    Set<Player> getByUserPaging(String username, int page);
    Player update(Player player, String username);
    void delete(Long playerId, String username);
}
