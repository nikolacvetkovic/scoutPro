package com.riocode.scoutpro.service.impl;

import com.riocode.scoutpro.dao.PlayerDao;
import com.riocode.scoutpro.exception.DuplicatePlayerException;
import com.riocode.scoutpro.exception.ParseException;
import com.riocode.scoutpro.exception.PlayerNotFoundException;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.WhoScoredInfo;
import com.riocode.scoutpro.service.PlayerService;
import com.riocode.scoutpro.service.async.PlayerServiceAsync;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

/**
 *
 * @author Nikola Cvetkovic
 */

@Component
@Transactional
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private PlayerServiceAsync playerServiceAsync;
        
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
        List<Player> l = playerDao.getByTransfermarktUrl(transfermarktUrl);
        if(l.isEmpty()) throw  new PlayerNotFoundException("transfermarktUrl", transfermarktUrl);
        
        return l.get(0);
    }      
                
    @Override
    public Player create(Player player){
        Future<Player> task = playerServiceAsync.create(player);
        
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException ex) {
            resolveAsyncExceptions(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Player update(Player player){
        Player p = playerDao.getById(player.getId());
        //ovde dodati proveru da li podaci postoje u bazi
        if(p == null) throw new PlayerNotFoundException("playerId", player.getId());
        player.setLastMeasured(LocalDateTime.now());
        
        return playerDao.update(player);        
    }

    @Override
    public Player updateTransfermarktInfo(int playerId) {
        Future<Player> task = playerServiceAsync.updateTransfermarktInfo(playerId);
        
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException ex) {
            resolveAsyncExceptions(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Player updateExistingWhoScoredInfo(int playerId) {
        Future<Player> task = playerServiceAsync.updateExistingWhoScoredInfo(playerId);
        
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException ex) {
            resolveAsyncExceptions(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Player updateExistingPesDbInfo(int playerId) {
        Future<Player> task = playerServiceAsync.updateExistingPesDbInfo(playerId);
        
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException ex) {
            resolveAsyncExceptions(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Player updateExistingPsmlInfo(int playerId) {
        Future<Player> task = playerServiceAsync.updateExistingPsmlInfo(playerId);
        
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException ex) {
            resolveAsyncExceptions(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Player createWhoScoredInfo(int playerId) {
        Future<Player> task = playerServiceAsync.createWhoScoredInfo(playerId);
        
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException ex) {
            resolveAsyncExceptions(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Player createPesDbInfo(int playerId) {
        Future<Player> task = playerServiceAsync.createPesDbInfo(playerId);
        
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException ex) {
            resolveAsyncExceptions(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Player createPsmlInfo(int playerId) {
        Future<Player> task = playerServiceAsync.createPsmlInfo(playerId);
        
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException ex) {
            resolveAsyncExceptions(ex);
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void delete(int id) {
        Player player = playerDao.getById(id);
        if(player == null) throw new PlayerNotFoundException("playerId", id);
        playerDao.delete(player);
    }
    
    private void resolveAsyncExceptions(Throwable ex){
        if(ex.getCause() != null && ex.getCause() instanceof PlayerNotFoundException) throw (RuntimeException)ex.getCause();
        if(ex.getCause() !=null && ex.getCause() instanceof DuplicatePlayerException) throw (RuntimeException)ex.getCause();
        if(ex.getCause() !=null && ex.getCause() instanceof ParseException) throw (RuntimeException)ex.getCause();
        if(ex.getCause() !=null && ex.getCause() instanceof TransactionSystemException) throw (RuntimeException)ex.getCause();
    }
    
}
