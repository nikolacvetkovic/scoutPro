package xyz.riocode.scoutpro.service;

import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.repository.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author Nikola Cvetkovic
 */

@Component
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player create(Player player) {
        return null;
    }

    @Override
    public Player getById(Long id) {
        return null;
    }

    @Override
    public List<Player> getByName(String name) {
        return null;
    }

    @Override
    public Player update(Player player) {
        return null;
    }

    @Override
    public void delete(int playerId) {

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
//        for(WhoScoredInfo ws : p.getWhoscoredInfoList()){
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
//            for(WhoScoredInfo ws : player.getWhoscoredInfoList()){
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
//    public Player getByTransfermarktUrl(String transfermarktUrl) {
//        List<Player> l = playerDao.getByTransfermarktUrl(transfermarktUrl);
//        if(l.isEmpty()) throw  new PlayerNotFoundException("transfermarktUrl", transfermarktUrl);
//
//        return l.get(0);
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
//    public Player update(Player player){
//        Player p = playerDao.getById(player.getId());
        //ovde dodati proveru da li podaci postoje u bazi
//        if(p == null) throw new PlayerNotFoundException("playerId", player.getId());
//        player.setLastMeasured(LocalDateTime.now());
        
//        return playerDao.update(player);
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
//        WhoScoredInfo ws = p.getWhoscoredInfoList().get(p.getWhoscoredInfoList().size()-1);
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
//
//    @Override
//    public void delete(int id) {
//        Player player = playerDao.getById(id);
//        if(player == null) throw new PlayerNotFoundException("playerId", id);
//        playerDao.delete(player);
//    }
//
//    private void resolveAsyncExceptions(Throwable ex){
//        if(ex.getCause() != null && ex.getCause() instanceof PlayerNotFoundException) throw (RuntimeException)ex.getCause();
//        if(ex.getCause() !=null && ex.getCause() instanceof DuplicatePlayerException) throw (RuntimeException)ex.getCause();
//        if(ex.getCause() !=null && ex.getCause() instanceof ParseException) throw (RuntimeException)ex.getCause();
//        if(ex.getCause() !=null && ex.getCause() instanceof TransactionSystemException) throw (RuntimeException)ex.getCause();
//        if(ex.getCause() !=null && ex.getCause() instanceof ConstraintViolationException) throw new TransactionSystemException("Constraint Validation Failed", ex.getCause());
//    }
    
}
