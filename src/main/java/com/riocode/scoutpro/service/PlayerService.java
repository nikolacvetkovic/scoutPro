package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.Player;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerService {
    Player create(Player player) throws IOException;
    Player update(Player player) throws IOException;
    
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
    
    void delete(int playerId);
}
