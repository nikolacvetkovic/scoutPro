package com.riocode.scoutpro.service.impl;

import com.riocode.scoutpro.crawler.template.impl.PesDbCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.PsmlCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.TransfermarktCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.WhoScoredCrawlTemplateImpl;
import com.riocode.scoutpro.dao.impl.PlayerDaoImpl;
import com.riocode.scoutpro.exception.PlayerNotFoundException;
import com.riocode.scoutpro.model.PesDbInfo;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.PsmlInfo;
import com.riocode.scoutpro.model.WhoScoredInfo;
import com.riocode.scoutpro.service.PlayerService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        return playerDao.getById(playerId);
    }

    @Override
    public Player getCompleteById(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null){
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
        }
        
        return p;
    }   
        
    @Override
    public Player create(Player player) throws IOException{
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
        if(p == null) throw new PlayerNotFoundException("Player not found.");
        player.setLastMeasured(LocalDateTime.now());
        
        return playerDao.update(player);        
    }

    @Override
    public Player updateTransfermarktInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null) {
            p.getTransfermarktInfo().getTransferList().size();
            p.getTransfermarktInfo().getTransferList().clear();
            p.getTransfermarktInfo().getMarketValueList().size();
            p.getTransfermarktInfo().getMarketValueList().clear();
            TransfermarktCrawlTemplateImpl tmCrawlTemplate = new TransfermarktCrawlTemplateImpl(p.getTransfermarktInfo());
            try {
                tmCrawlTemplate.start();
            } catch (IOException ex) {
                Logger.getLogger(PlayerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    @Override
    public Player updateExistingWhoScoredInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null) {
            WhoScoredInfo ws = p.getWhoscoredInfoList().get(p.getWhoscoredInfoList().size()-1);
            ws.getCoreStatsList().size();
            ws.getCoreStatsList().clear();
            ws.getPositionPlayedStatsList().size();
            ws.getPositionPlayedStatsList().clear();
            ws.getGameList().size();
            ws.getGameList().clear();
            WhoScoredCrawlTemplateImpl wsCrawlTemplate = new WhoScoredCrawlTemplateImpl(ws);
            try {
                wsCrawlTemplate.start();
            } catch (IOException ex) {
                Logger.getLogger(PlayerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    @Override
    public Player updateExistingPesDbInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null){
            PesDbInfo pesDb = p.getPesDbInfoList().get(p.getPesDbInfoList().size()-1);
            PesDbCrawlTemplateImpl pesDbCrawlTemplate = new PesDbCrawlTemplateImpl(pesDb);
            try {
                pesDbCrawlTemplate.start();
            } catch (IOException ex) {
                Logger.getLogger(PlayerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return p;
    }

    @Override
    public Player updateExistingPsmlInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null){
            PsmlInfo psml = p.getPsmlInfoList().get(p.getPsmlInfoList().size()-1);
            PsmlCrawlTemplateImpl psmlCrawlTemplate = new PsmlCrawlTemplateImpl(psml);
            try {
                psmlCrawlTemplate.start();
            } catch (IOException ex) {
                Logger.getLogger(PlayerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    @Override
    public Player createWhoScoredInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null){
            WhoScoredCrawlTemplateImpl wsDbCrawlTemplate = new WhoScoredCrawlTemplateImpl(p);
            try {
                wsDbCrawlTemplate.start();
            } catch (IOException ex) {
                Logger.getLogger(PlayerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    @Override
    public Player createPesDbInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null){
            PesDbCrawlTemplateImpl pesDbCrawlTemplate = new PesDbCrawlTemplateImpl(p);
            try {
                pesDbCrawlTemplate.start();
            } catch (IOException ex) {
                Logger.getLogger(PlayerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    @Override
    public Player createPsmlInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p != null){
            PsmlCrawlTemplateImpl psmlCrawlTemplate = new PsmlCrawlTemplateImpl(p);
            try {
                psmlCrawlTemplate.start();
            } catch (IOException ex) {
                Logger.getLogger(PlayerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }
    
    @Override
    public void delete(int id) {
        Player player = playerDao.getById(id);
        if(player == null) throw new PlayerNotFoundException("Player not found.");
        playerDao.delete(player);
    }
    
}
