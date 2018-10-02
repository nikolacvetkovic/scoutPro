package com.riocode.scoutpro.service.async;

import com.riocode.scoutpro.model.Player;
import java.util.concurrent.Future;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerServiceAsync {
    Future<Player> create(Player player);

    Future<Player> createWhoScoredInfo(int playerId);
    Future<Player> createPesDbInfo(int playerId);
    Future<Player> createPsmlInfo(int playerId);
}
