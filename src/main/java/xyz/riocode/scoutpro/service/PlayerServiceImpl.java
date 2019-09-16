package xyz.riocode.scoutpro.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.riocode.scoutpro.exception.AppUserNotFoundException;
import xyz.riocode.scoutpro.exception.DuplicatePlayerException;
import xyz.riocode.scoutpro.exception.PlayerNotFoundException;
import xyz.riocode.scoutpro.model.AppUser;
import xyz.riocode.scoutpro.model.AppUserPlayer;
import xyz.riocode.scoutpro.model.AppUserPlayerId;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.repository.AppUserPlayerRepository;
import xyz.riocode.scoutpro.repository.AppUserRepository;
import xyz.riocode.scoutpro.repository.PlayerRepository;
import xyz.riocode.scoutpro.scrape.template.async.ScrapeAsyncWrapper;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserPlayerRepository appUserPlayerRepository;
    private final ScrapeAsyncWrapper scrapeAsyncWrapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, AppUserRepository appUserRepository, AppUserPlayerRepository appUserPlayerRepository, ScrapeAsyncWrapper scrapeAsyncWrapper) {
        this.playerRepository = playerRepository;
        this.appUserRepository = appUserRepository;
        this.appUserPlayerRepository = appUserPlayerRepository;
        this.scrapeAsyncWrapper = scrapeAsyncWrapper;
    }

    @Override
    public Player createOrUpdate(Player player, String username) {
        if(player.getId() == null) {
            return createPlayerAndAddToUser(player, username);
        } else {
            return update(player, username);
        }
    }

    @Override
    public Player addExistingPlayerToUser(Long id, boolean isUserPlayer, String username) {
        Player foundPlayer = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
        AppUser foundUser = appUserRepository.findByUsername(username).orElseThrow(AppUserNotFoundException::new);

        AppUserPlayerId appUserPlayerId = new AppUserPlayerId();
        AppUserPlayer appUserPlayer = new AppUserPlayer();
        appUserPlayer.setAppUserPlayerId(appUserPlayerId);
        appUserPlayer.setPlayer(foundPlayer);
        appUserPlayer.setAppUser(foundUser);
        appUserPlayer.setMyPlayer(isUserPlayer);

        appUserPlayerRepository.save(appUserPlayer);

        return appUserPlayer.getPlayer();
    }

    @Override
    public Player getByIdAndUser(Long id, String username) {
        return playerRepository.findPlayerByIdAndUsername(id, username).orElseThrow(PlayerNotFoundException::new);
    }

    @Override
    public Player getByIdAndUserComplete(Long id, String username) {
        return playerRepository.findPlayerByIdAndUsernameComplete(id, username).orElseThrow(PlayerNotFoundException::new);
    }

    @Override
    public Set<Player> getByNameAndUser(String playerName, String username) {
        return new HashSet<>(playerRepository.findByPlayerNameAndUsername(playerName, username));
    }

    @Override
    public Set<Player> getByUserPaging(String username, int page) {
        return new HashSet<>(playerRepository.findPlayersByUsername(username, PageRequest.of(page, 25)));
    }

    @Override
    public void delete(Long playerId, String username) {
        Player foundPlayer = playerRepository.findPlayerByIdAndUsername(playerId, username).orElseThrow(PlayerNotFoundException::new);

        AppUserPlayer appUserPlayer = foundPlayer.getUsers().stream().findFirst().get();
        AppUser appUser = appUserPlayer.getAppUser();

        foundPlayer.removeUser(appUserPlayer);
        appUser.removePlayer(appUserPlayer);

        playerRepository.save(foundPlayer);
    }

    private Player createPlayerAndAddToUser(Player player, String username){
        Player foundPlayer = playerRepository.findByTransfermarktUrl(player.getTransfermarktUrl());
        if(foundPlayer != null) throw new DuplicatePlayerException();

        AppUser appUser = appUserRepository.findByUsername(username).get();
        player.getUsers().stream().findFirst().get().setAppUser(appUser);

        CompletableFuture<Player> tmAll = scrapeAsyncWrapper.tmAllScrape(player);
        CompletableFuture<Player> pesDb = scrapeAsyncWrapper.pesDbScrape(player);
        CompletableFuture<Player> wsAll = scrapeAsyncWrapper.wsAllScrape(player);
        CompletableFuture<Player> psml = scrapeAsyncWrapper.psmlScrape(player);

        CompletableFuture.allOf(tmAll, pesDb, wsAll, psml).join();
        Player p = null;

        try {
            p = tmAll.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return playerRepository.save(p);
    }

    private Player update(Player player, String username) {
        Player foundPlayer = playerRepository.findPlayerByIdAndUsername(player.getId(), username).orElseThrow(PlayerNotFoundException::new);

        AppUserPlayer foundAppUserPlayer = foundPlayer.getUsers().stream().findFirst().get();
        foundAppUserPlayer.setMyPlayer(player.getUsers().stream().findFirst().get().isMyPlayer());

        return playerRepository.save(foundPlayer);
    }
}
