package com.riocode.scoutpro.service.impl;

import com.riocode.scoutpro.crawler.template.impl.TransfermarktCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.WhoScoredCrawlTemplateImpl;
import com.riocode.scoutpro.dao.PlayerDao;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.service.PlayerService;
import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nikola Cvetkovic
 */

@Component
@Transactional
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerDao playerDao;
    
    @Async
    @Override
    public Player create(Player player) throws IOException{
        if(player.getTransfermarktUrl() != null){
            WhoScoredCrawlTemplateImpl wscti = new WhoScoredCrawlTemplateImpl(player);
            wscti.start();
        }
        
        return playerDao.create(player);
        
    }

    @Override
    public Player getById(int id) {
        return playerDao.getById(id);
    }
        
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        Player player = playerDao.getById(id);
        playerDao.delete(player);
    }

}
