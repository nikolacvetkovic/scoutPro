package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.Player;

import java.util.List;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerService {
    Player create(Player player);
    Player getById(Long id);
    List<Player> getByName(String name);
    Player update(Player player);
    void delete(int playerId);
}
