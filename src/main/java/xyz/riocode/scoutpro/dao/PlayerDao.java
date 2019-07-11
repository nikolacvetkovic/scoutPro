package xyz.riocode.scoutpro.dao;

import xyz.riocode.scoutpro.model.Player;

import java.util.List;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerDao {
    Player create(Player player);
    List<Player> getAll();
    Player getById(int id);
    List<Player> getByName(String name);
    List<Player> getByTransfermarktUrl(String transfermarktUrl);
    Player update(Player player);
    void delete(Player player);
}
