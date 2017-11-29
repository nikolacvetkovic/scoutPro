package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.Player;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerService {
//    Player create(String name, String transfermarktUrl, String whoScoredUrl, String pesDbUrl, String psmlUrl);
    Player create(Player player);
    Player getById(int id);
    void update();
    void delete(int id);
}
