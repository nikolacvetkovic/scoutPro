package com.riocode.scoutpro.service.async.impl;

import com.riocode.scoutpro.crawler.template.impl.PesDbCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.PsmlCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.TransfermarktCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.WhoScoredCrawlTemplateImpl;
import com.riocode.scoutpro.dao.PlayerDao;
import com.riocode.scoutpro.exception.DuplicatePlayerException;
import com.riocode.scoutpro.exception.PlayerNotFoundException;
import com.riocode.scoutpro.model.PesDbInfo;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.PsmlInfo;
import com.riocode.scoutpro.model.WhoScoredInfo;
import com.riocode.scoutpro.service.async.PlayerServiceAsync;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Future;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nikola Cvetkovic
 */

@Component
@Transactional
public class PlayerServiceAsyncImpl implements PlayerServiceAsync{

    @Autowired
    private PlayerDao playerDao;
    
    @Override
    public Future<Player> create(Player player) {
        List<Player> l = playerDao.getByTransfermarktUrl(player.getTransfermarktUrl()); //lose jer transfermarkt teorijski moze biti null
        if(l.size() > 0) throw new DuplicatePlayerException();
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
        return new AsyncResult<>(playerDao.create(player));
    }

    @Override
    public Future<Player> createWhoScoredInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        WhoScoredCrawlTemplateImpl wsDbCrawlTemplate = new WhoScoredCrawlTemplateImpl(p);
        wsDbCrawlTemplate.start();
        return new AsyncResult<>(p);
    }

    @Override
    public Future<Player> createPesDbInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        PesDbCrawlTemplateImpl pesDbCrawlTemplate = new PesDbCrawlTemplateImpl(p);
        pesDbCrawlTemplate.start();
        return new AsyncResult<>(p);
    }

    @Override
    public Future<Player> createPsmlInfo(int playerId) {
        Player p = playerDao.getById(playerId);
        if(p == null) throw  new PlayerNotFoundException("playerId", playerId);
        PsmlCrawlTemplateImpl psmlCrawlTemplate = new PsmlCrawlTemplateImpl(p);
        psmlCrawlTemplate.start();
        return new AsyncResult<>(p);
    }
    
}
