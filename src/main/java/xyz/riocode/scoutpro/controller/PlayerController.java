package xyz.riocode.scoutpro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.service.PlayerService;

import java.util.Set;

/**
 *
 * @author Nikola Cvetkovic
 */

@Controller
@RequestMapping("/player")
public class PlayerController {
    
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/new")
    public String showPlayerForm(ModelMap modelMap){
        return "playerForm";
    }

    @PostMapping("/new")
    public String save(Player player, ModelMap modelMap){
        return "playerForm";
    }

    @GetMapping(value = "/{pageNumber}/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Player>> getPlayers(@PathVariable int pageNumber){
        return new ResponseEntity<>(playerService.getByUserPaging("cvele", pageNumber), HttpStatus.OK);
    }

    @GetMapping("/{playerId}/edit")
    public String showPlayerFormForEdit(@PathVariable Long playerId, ModelMap modelMap){
        return "playerForm";
    }

    @PostMapping("/edit")
    public String update(Player player, ModelMap modelMap){
        return "";
    }

    @GetMapping(value = "/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId, ModelMap modelMap){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{playerId}/show")
    public String show(@PathVariable Long playerId, ModelMap modelMap){
        return "";
    }

    @GetMapping("/{playerName}/name")
    public String getPlayerByName(@PathVariable Long playerName, ModelMap modelMap){
        return "";
    }

    @GetMapping("/{playerId}/compare")
    public String compare(@PathVariable Long playerId, ModelMap modelMap){
        return "";
    }

    @GetMapping("/{playerId}/delete")
    public String delete(@PathVariable Long playerId){
        return "";
    }

//    // Get All Players
//    @RequestMapping(value = "/player", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<Player> allPlayers(){
//
//        return playerService.getAll();
//    }
//    // Get All Complete Players
//    @RequestMapping(value = "/player/complete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<Player> allCompletePlayers(){
//
//        return playerService.getAllComplete();
//    }
//    // Get One Complete Player
//    @RequestMapping(value = "/player/complete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Player oneCompletePlayer(@PathVariable int id){
//
//        return playerService.getCompleteById(id);
//    }
//    // Get One Core Player
//    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Player onePlayer(@PathVariable int id){
//
//        return playerService.getById(id);
//    }
//
//    @RequestMapping(value = "/player/search/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public List<Player> playersByName(@PathVariable String name){
//
//        return playerService.getByName(name);
//    }
//    // Create Player
//    @RequestMapping(value = "/player", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Player create(@Valid Player player) {
//
//        return playerService.create(player);
//    }
//    // Update Core Player
//    @RequestMapping(value = "/player/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Player update(@Valid Player player){
//
//        return playerService.update(player);
//    }
//    // Update Transfermarkt
//    @RequestMapping(value = "/player/transfermarkt/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Player updateTransfermarktInfo(@PathVariable int id){
//
//        return playerService.updateTransfermarktInfo(id);
//    }
//    // Update last WhoScored
//    @RequestMapping(value = "/player/whoscored/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Player updateWhoScoredInfo(@PathVariable int id){
//
//        return playerService.updateExistingWhoScoredInfo(id);
//    }
//    // Update last PesDb
//    @RequestMapping(value = "/player/pesdb/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Player updatePesDbInfo(@PathVariable int id){
//
//        return playerService.updateExistingPesDbInfo(id);
//    }
//    // Update last Psml
//    @RequestMapping(value = "/player/psml/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public Player updatePsmlInfo(@PathVariable int id){
//
//        return playerService.updateExistingPsmlInfo(id);
//    }
//    // Delete Player
//    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity deletePlayer(@PathVariable int id){
//
//        playerService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
    
}
