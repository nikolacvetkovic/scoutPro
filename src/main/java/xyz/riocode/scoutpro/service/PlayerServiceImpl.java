package xyz.riocode.scoutpro.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import xyz.riocode.scoutpro.exception.PlayerNotFoundException;
import xyz.riocode.scoutpro.model.AppUser;
import xyz.riocode.scoutpro.model.AppUserPlayer;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.repository.PlayerRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player create(Player player, String username) {
        return null;
    }

    @Override
    public Player getByIdAndUser(Long id, String username) {
        Player playerFound = playerRepository.findPlayerByIdAndUsername(id, username).orElseThrow(PlayerNotFoundException::new);
        return playerFound;
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
    public Player update(Player player, String username) {
        Player foundPlayer = playerRepository.findPlayerByIdAndUsername(player.getId(), username).orElseThrow(PlayerNotFoundException::new);

        AppUserPlayer foundAppUserPlayer = foundPlayer.getUsers().stream().findFirst().get();
        foundAppUserPlayer.setMyPlayer(player.getUsers().stream().findFirst().get().isMyPlayer());

        return playerRepository.save(foundPlayer);
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


//    @Autowired
//    private PlayerDao playerDao;
//    @Autowired
//    private PlayerServiceAsync playerServiceAsync;
//
//    @Override
//    public List<Player> getAll() {
//        return playerDao.getAll();
//    }
//
//    @Override
//    public Player getById(int playerId) {
//        Player p = playerDao.getById(playerId);
//        if(p == null) throw new PlayerNotFoundException("playerId", playerId);
//        return p;
//    }
//
//    @Override
//    public Player getCompleteById(int playerId) {
//        Player p = playerDao.getById(playerId);
//        if(p == null) throw new PlayerNotFoundException("playerId", playerId);
//        p.getTransfermarktInfo().getTransferList().size();
//        p.getTransfermarktInfo().getMarketValueList().size();
//        p.getWhoscoredInfoList().size();
//        for(Characteristic ws : p.getWhoscoredInfoList()){
//            ws.getCoreStatsList().size();
//            ws.getPositionPlayedStatsList().size();
//            ws.getGameList().size();
//        }
//        p.getPesDbInfoList().size();
//        p.getPsmlInfoList().size();
        
//        return p;
//    }
//
//    @Override
//    public List<Player> getAllComplete() {
//        List<Player> players = playerDao.getAll();
//        for (Player player : players) {
//            player.getTransfermarktInfo().getTransferList().size();
//            player.getTransfermarktInfo().getMarketValueList().size();
//            player.getWhoscoredInfoList().size();
//            for(Characteristic ws : player.getWhoscoredInfoList()){
//                ws.getCoreStatsList().size();
//                ws.getPositionPlayedStatsList().size();
//                ws.getGameList().size();
//            }
//            player.getPesDbInfoList().size();
//            player.getPsmlInfoList().size();
//        }
        
//        return players;
//    }
//
//
//    @Override
//    public List<Player> getByName(String name) {
//        List<Player> p = playerDao.getByName(name);
//        if(p == null) throw  new PlayerNotFoundException("playerName", name);
//        return p;
//    }
//
//    @Override
//    public Player create(Player player){
//        List<Player> l = playerDao.getByTransfermarktUrl(player.getTransfermarktUrl()); //lose jer transfermarkt teorijski moze biti null
//        if(l.size() > 0) throw new DuplicatePlayerException();
//
//        CompletableFuture<Player> p1 = playerServiceAsync.createTransfermarktInfo(player);
//        CompletableFuture<Player> p2 = playerServiceAsync.createWhoScoredInfo(player);
//        CompletableFuture<Player> p3 = playerServiceAsync.createPesDbInfo(player);
//        CompletableFuture<Player> p4 = playerServiceAsync.createPsmlInfo(player);
//
//        CompletableFuture.allOf(p1, p2, p3, p4).join();
//        Player p;
//        try {
//            p = p1.get();
//            p.setLastMeasured(LocalDateTime.now());
//        } catch (InterruptedException | ExecutionException ex) {
//            throw new RuntimeException(ex);
//        }
//
//        return playerDao.create(p);
//    }
//
//    @Override
//    public Player updateTransfermarktInfo(int playerId) {
//        Player p = playerDao.getById(playerId);
//        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
//        p.getTransfermarktInfo().getTransferList().size();
//        p.getTransfermarktInfo().getTransferList().clear();
//        p.getTransfermarktInfo().getMarketValueList().size();
//        p.getTransfermarktInfo().getMarketValueList().clear();
//        p.getPsmlInfoList().size();
//        TransfermarktScrapeTemplateImpl tmCrawlTemplate = new TransfermarktScrapeTemplateImpl(p.getTransfermarktInfo());
//        tmCrawlTemplate.start();
//        return p;
//    }
//
//    @Override
//    public Player updateExistingWhoScoredInfo(int playerId) {
//        Player p = playerDao.getById(playerId);
//        if(p == null) throw new PlayerNotFoundException("playerId", playerId);
//        Characteristic ws = p.getWhoscoredInfoList().get(p.getWhoscoredInfoList().size()-1);
//        ws.getCoreStatsList().size();
//        ws.getCoreStatsList().clear();
//        ws.getPositionPlayedStatsList().size();
//        ws.getPositionPlayedStatsList().clear();
//        ws.getGameList().size();
//        ws.getGameList().clear();
//        WhoScoredScrapeTemplateImpl wsCrawlTemplate = new WhoScoredScrapeTemplateImpl(ws);
//        wsCrawlTemplate.start();
//        return p;
//    }
//
//    @Override
//    public Player updateExistingPesDbInfo(int playerId) {
//        Player p = playerDao.getById(playerId);
//        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
//        PesDbInfo pesDb = p.getPesDbInfoList().get(p.getPesDbInfoList().size()-1);
//        PesDbScrapeTemplateImpl pesDbCrawlTemplate = new PesDbScrapeTemplateImpl(pesDb);
//        pesDbCrawlTemplate.start();
//        return p;
//    }
//
//    @Override
//    public Player updateExistingPsmlInfo(int playerId) {
//        Player p = playerDao.getById(playerId);
//        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
//        PsmlInfo psml = p.getPsmlInfoList().get(p.getPsmlInfoList().size()-1);
//        PsmlScrapeTemplateImpl psmlCrawlTemplate = new PsmlScrapeTemplateImpl(psml);
//        psmlCrawlTemplate.start();
//        return p;
//    }
    
}
