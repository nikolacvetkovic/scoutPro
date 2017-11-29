package com.riocode.scoutpro.service.impl;

import com.riocode.scoutpro.dao.PlayerDao;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.service.PlayerService;
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
    private PlayerDao playerDao;
    
//    @Override
//    public Player create(String name, String transfermarktUrl, String whoScoredUrl, String pesDbUrl, String psmlUrl) {
//        // kreira player-a i persistuje ga
//        // proveri da li su url-ovi razliciti od null i za one koji su razliciti kreira template i odgovarajuci job
//        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public Player create(Player player) {
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
