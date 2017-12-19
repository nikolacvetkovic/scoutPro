package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.service.impl.PlayerServiceImpl;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nikola Cvetkovic
 */

@RestController
@RequestMapping("/")
public class PlayerController {
    
    @Autowired
    private PlayerServiceImpl playerService;
    
    // Get All Players
    @RequestMapping(value = "/player", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Player> allPlayers(){
        
        return playerService.getAll();
    }
    // Get One Complete Player
    @RequestMapping(value = "/player/complete/{id}", method = RequestMethod.GET)
    public Player oneCompletePlayer(@PathVariable int id){
        
        return playerService.getCompleteById(id);
    }
    // Get One Core Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public Player onePlayer(@PathVariable int id){
        
        return playerService.getById(id);
    }
    // Create Player
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public Player create(Player player) throws IOException{
        
        return playerService.create(player);
    }
    // Update Core Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT)
    public Player update(Player player) throws IOException{
                
        return playerService.update(player);
    }
    // Update Transfermarkt
    @RequestMapping(value = "/player/transfermarkt/{id}", method = RequestMethod.PUT)
    public Player updateTransfermarktInfo(@PathVariable int id){
        
        return playerService.updateTransfermarktInfo(id);
    }
    // Update last WhoScored
    @RequestMapping(value = "/player/whoscored/{id}", method = RequestMethod.PUT)
    public Player updateWhoScoredInfo(@PathVariable int id){
        
        return playerService.updateExistingWhoScoredInfo(id);
    }
    // Update last PesDb
    @RequestMapping(value = "/player/pesdb/{id}", method = RequestMethod.PUT)
    public Player updatePesDbInfo(@PathVariable int id){
        
        return playerService.updateExistingPesDbInfo(id);
    }
    // Update last Psml
    @RequestMapping(value = "/player/psml/{id}", method = RequestMethod.PUT)
    public Player updatePsmlInfo(@PathVariable int id){
        
        return playerService.updateExistingPsmlInfo(id);
    }
    // Create WhoScored
    @RequestMapping(value = "/player/whoscored/{id}", method = RequestMethod.POST)
    public Player createWhoScoredInfo(@PathVariable int id){
        
        return playerService.createWhoScoredInfo(id);
    }    
    // Create PesDb
    @RequestMapping(value = "/player/pesdb/{id}", method = RequestMethod.POST)
    public Player createPesDbInfo(@PathVariable int id){
        
        return playerService.createPesDbInfo(id);
    }
    // Create Psml
    @RequestMapping(value = "/player/psml/{id}", method = RequestMethod.POST)
    public Player createPsmlInfo(@PathVariable int id){
        
        return playerService.createPsmlInfo(id);
    }
    // Delete Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePlayer(@PathVariable int id){
        
        playerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
