package com.riocode.scoutpro.service.impl;

import com.riocode.scoutpro.crawler.template.impl.PesDbCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.PsmlCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.TransfermarktCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.WhoScoredCrawlTemplateImpl;
import com.riocode.scoutpro.dao.impl.PlayerDaoImpl;
import com.riocode.scoutpro.exception.DuplicatePlayerException;
import com.riocode.scoutpro.exception.PlayerNotFoundException;
import com.riocode.scoutpro.model.PesDbInfo;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.PsmlInfo;
import com.riocode.scoutpro.model.WhoScoredInfo;
import com.riocode.scoutpro.service.PlayerService;
import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nikola Cvetkovic
 */

@Component
@Transactional
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerDaoImpl playerDao;
        
    @Override
    public List<Player> getAll() {
        return playerDao.getAll();
    }

    @Override
    public Player getById(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw new PlayerNotFoundException("playerId", playerId);
        return p;
    }

    @Override
    public Player getCompleteById(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw new PlayerNotFoundException("playerId", playerId);
        p.getTransfermarktInfo().getTransferList().size();
        p.getTransfermarktInfo().getMarketValueList().size();
        p.getWhoscoredInfoList().size();
        for(WhoScoredInfo ws : p.getWhoscoredInfoList()){
            ws.getCoreStatsList().size();
            ws.getPositionPlayedStatsList().size();
            ws.getGameList().size();
        }
        p.getPesDbInfoList().size();
        p.getPsmlInfoList().size();
        
        return p;
    }

    @Override
    public List<Player> getByName(String name) {
        List<Player> p = playerDao.getByName(name);
        if(p == null) throw  new PlayerNotFoundException("playerName", name);
        return p;
    }

    @Override
    public Player getByTransfermarktUrl(String transfermarktUrl) {
        Player p = playerDao.getByTransfermarktUrl(transfermarktUrl);
        if(p == null) throw  new PlayerNotFoundException("transfermarktUrl", transfermarktUrl);
        
        return p;
    }      
                
    @Override
    public Player create(Player player){
        Player p = playerDao.getByTransfermarktUrl(player.getTransfermarktUrl()); //lose jer transfermarkt teorijski moze biti null
        if(p != null) throw new DuplicatePlayerException();
        if(!player.getTransfermarktUrl().equals("")){
            TransfermarktCrawlTemplateImpl tmCrawlTemplate = new TransfermarktCrawlTemplateImpl(player);
            tmCrawlTemplate.start();
        }
        if(!player.getWhoScoredUrl().equals("")){
            WhoScoredCrawlTemplateImpl wsCrawlTemplate = new WhoScoredCrawlTemplateImpl(player);
            wsCrawlTemplate.start();
        }
        if(!player.getPesDbUrl().equals("")){
            PesDbCrawlTemplateImpl pesDbCrawlTemplate = new PesDbCrawlTemplateImpl(player);
            pesDbCrawlTemplate.start();
        }
        if(!player.getPsmlUrl().equals("")){
            PsmlCrawlTemplateImpl psmlCrawlTemplate = new PsmlCrawlTemplateImpl(player);
            psmlCrawlTemplate.start();
        }
        player.setLastMeasured(LocalDateTime.now());
        return playerDao.create(player);
        
    }

    @Override
    public Player update(Player player){
        Player p = playerDao.getById(player.getId());
        if(p == null) throw new PlayerNotFoundException("playerId", player.getId());
        player.setLastMeasured(LocalDateTime.now());
        
        return playerDao.update(player);        
    }

    @Override
    public Player updateTransfermarktInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        p.getTransfermarktInfo().getTransferList().size();
        p.getTransfermarktInfo().getTransferList().clear();
        p.getTransfermarktInfo().getMarketValueList().size();
        p.getTransfermarktInfo().getMarketValueList().clear();
        TransfermarktCrawlTemplateImpl tmCrawlTemplate = new TransfermarktCrawlTemplateImpl(p.getTransfermarktInfo());
        tmCrawlTemplate.start();
        return p;
    }

    @Override
    public Player updateExistingWhoScoredInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null) throw  new PlayerNotFoundException("playerId", playerId);
        WhoScoredInfo ws = p.getWhoscoredInfoList().get(p.getWhoscoredInfoList().size()-1);
        ws.getCoreStatsList().size();
        ws.getCoreStatsList().clear();
        ws.getPositionPlayedStatsList().size();
        ws.getPositionPlayedStatsList().clear();
        ws.getGameList().size();
        ws.getGameList().clear();
        WhoScoredCrawlTemplateImpl wsCrawlTemplate = new WhoScoredCrawlTemplateImpl(ws);
        wsCrawlTemplate.start();
        return p;
    }

    @Override
    public Player updateExistingPesDbInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        PesDbInfo pesDb = p.getPesDbInfoList().get(p.getPesDbInfoList().size()-1);
        PesDbCrawlTemplateImpl pesDbCrawlTemplate = new PesDbCrawlTemplateImpl(pesDb);
        pesDbCrawlTemplate.start();
        return p;
    }

    @Override
    public Player updateExistingPsmlInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        PsmlInfo psml = p.getPsmlInfoList().get(p.getPsmlInfoList().size()-1);
        PsmlCrawlTemplateImpl psmlCrawlTemplate = new PsmlCrawlTemplateImpl(psml);
        psmlCrawlTemplate.start();
        return p;
    }

    @Override
    public Player createWhoScoredInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        WhoScoredCrawlTemplateImpl wsDbCrawlTemplate = new WhoScoredCrawlTemplateImpl(p);
        wsDbCrawlTemplate.start();
        return p;
    }

    @Override
    public Player createPesDbInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        PesDbCrawlTemplateImpl pesDbCrawlTemplate = new PesDbCrawlTemplateImpl(p);
        pesDbCrawlTemplate.start();
        return p;
    }

    @Override
    public Player createPsmlInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        PsmlCrawlTemplateImpl psmlCrawlTemplate = new PsmlCrawlTemplateImpl(p);
        psmlCrawlTemplate.start();
        return p;
    }
    
    @Override
    public void delete(int id) {
        Player player = playerDao.getById(id);
        if(player == null) throw new PlayerNotFoundException("playerId", id);
        playerDao.delete(player);
    }
    
}
