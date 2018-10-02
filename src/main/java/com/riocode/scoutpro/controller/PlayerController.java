package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.service.PlayerService;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
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
    private PlayerService playerService;
    
    // Get All Players
    @RequestMapping(value = "/player", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Player> allPlayers(){
        
        return playerService.getAll();
    }
    // Get All Complete Players
    @RequestMapping(value = "/player/complete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Player> allCompletePlayers(){
        
        return playerService.getAllComplete();
    }
    // Get One Complete Player
    @RequestMapping(value = "/player/complete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player oneCompletePlayer(@PathVariable int id){
        
        return playerService.getCompleteById(id);
    }
    // Get One Core Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player onePlayer(@PathVariable int id){
        
        return playerService.getById(id);
    }
    
    @RequestMapping(value = "/player/search/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Player> playersByName(@PathVariable String name){
        
        return playerService.getByName(name);
    }
    // Create Player
    @RequestMapping(value = "/player", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player create(@Valid Player player) {
        
        return playerService.create(player);
    }
    // Update Core Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player update(@Valid Player player){
                
        return playerService.update(player);
    }
    // Update Transfermarkt
    @RequestMapping(value = "/player/transfermarkt/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player updateTransfermarktInfo(@PathVariable int id){
        
        return playerService.updateTransfermarktInfo(id);
    }
    // Update last WhoScored
    @RequestMapping(value = "/player/whoscored/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player updateWhoScoredInfo(@PathVariable int id){
        
        return playerService.updateExistingWhoScoredInfo(id);
    }
    // Update last PesDb
    @RequestMapping(value = "/player/pesdb/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player updatePesDbInfo(@PathVariable int id){
        
        return playerService.updateExistingPesDbInfo(id);
    }
    // Update last Psml
    @RequestMapping(value = "/player/psml/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player updatePsmlInfo(@PathVariable int id){
        
        return playerService.updateExistingPsmlInfo(id);
    }
    // Delete Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deletePlayer(@PathVariable int id){

        playerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
