package xyz.riocode.scoutpro.service.async;

import xyz.riocode.scoutpro.model.Player;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Nikola Cvetkovic
 * @deprecated
 */

public interface PlayerServiceAsync {
    CompletableFuture<Player> createTransfermarktInfo(Player player);
    CompletableFuture<Player> createWhoScoredInfo(Player player);
    CompletableFuture<Player> createPesDbInfo(Player player);
    CompletableFuture<Player> createPsmlInfo(Player player);
}
