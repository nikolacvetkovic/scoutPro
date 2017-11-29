package com.riocode.scoutpro.dao;

import com.riocode.scoutpro.model.Player;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerDao {
    Player create(Player player);
    Player getById(int id);
    void update(Player player);
    void delete(Player player);
}
