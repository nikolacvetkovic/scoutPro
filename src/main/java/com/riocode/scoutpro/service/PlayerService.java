package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.Player;
import java.io.IOException;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerService {
//    Player create(String name, String transfermarktUrl, String whoScoredUrl, String pesDbUrl, String psmlUrl);
    Player create(Player player) throws IOException;
    Player getById(int id);
    void update();
    void delete(int id);
}
