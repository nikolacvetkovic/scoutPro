package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.Player;
import java.util.List;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerService {
    Player create(Player player);
    Player update(Player player);
    
    Player updateTransfermarktInfo(int playerId);
    Player updateExistingWhoScoredInfo(int playerId);
    Player updateExistingPesDbInfo(int playerId);
    Player updateExistingPsmlInfo(int playerId);
    
    Player createWhoScoredInfo(int playerId);
    Player createPesDbInfo(int playerId);
    Player createPsmlInfo(int playerId);
    
    List<Player> getAll();
    Player getById(int playerId);
    Player getCompleteById(int playerId);
    List<Player> getByName(String name);
    Player getByTransfermarktUrl(String transfermarktUrl);
    
    void delete(int playerId);
}
