package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.exception.PlayerNotFoundException;
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
    @RequestMapping(value = "/player", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Player> allPlayers(){
        
        return playerService.getAll();
    }
    // Get One Complete Player
    @RequestMapping(value = "/player/complete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player oneCompletePlayer(@PathVariable int id){
        Player p = playerService.getCompleteById(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }
    // Get One Core Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player onePlayer(@PathVariable int id){
        Player p = playerService.getById(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }
    // Create Player
    @RequestMapping(value = "/player", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player create(Player player) throws IOException{
        
        return playerService.create(player);
    }
    // Update Core Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player update(Player player){
                
        return playerService.update(player);
    }
    // Update Transfermarkt
    @RequestMapping(value = "/player/transfermarkt/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player updateTransfermarktInfo(@PathVariable int id){
        Player p = playerService.updateTransfermarktInfo(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }
    // Update last WhoScored
    @RequestMapping(value = "/player/whoscored/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player updateWhoScoredInfo(@PathVariable int id){
        Player p = playerService.updateExistingWhoScoredInfo(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }
    // Update last PesDb
    @RequestMapping(value = "/player/pesdb/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player updatePesDbInfo(@PathVariable int id){
        Player p = playerService.updateExistingPesDbInfo(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }
    // Update last Psml
    @RequestMapping(value = "/player/psml/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player updatePsmlInfo(@PathVariable int id){
        Player p = playerService.updateExistingPsmlInfo(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }
    // Create WhoScored
    @RequestMapping(value = "/player/whoscored/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player createWhoScoredInfo(@PathVariable int id){
        Player p = playerService.createWhoScoredInfo(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }    
    // Create PesDb
    @RequestMapping(value = "/player/pesdb/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player createPesDbInfo(@PathVariable int id){
        Player p = playerService.createPesDbInfo(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }
    // Create Psml
    @RequestMapping(value = "/player/psml/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Player createPsmlInfo(@PathVariable int id){
        Player p = playerService.createPsmlInfo(id);
        if(p == null) throw new PlayerNotFoundException("Player with id=" + id + " not found.");
        
        return p;
    }
    // Delete Player
    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deletePlayer(@PathVariable int id){
        
        playerService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    
}
