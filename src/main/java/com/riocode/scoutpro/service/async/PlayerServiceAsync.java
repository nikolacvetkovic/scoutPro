package com.riocode.scoutpro.service.async;

import com.riocode.scoutpro.model.Player;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface PlayerServiceAsync {
    CompletableFuture<Player> createTransfermarktInfo(Player player);
    CompletableFuture<Player> createWhoScoredInfo(Player player);
    CompletableFuture<Player> createPesDbInfo(Player player);
    CompletableFuture<Player> createPsmlInfo(Player player);
}
