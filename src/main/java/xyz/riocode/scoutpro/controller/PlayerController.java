package xyz.riocode.scoutpro.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.service.PlayerService;

/**
 *
 * @author Nikola Cvetkovic
 */

@RestController
@RequestMapping("/player")
public class PlayerController {
    
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/new")
    public String getPlayerForm(ModelMap modelMap){
        return "playerForm";
    }

    @PostMapping("/new")
    public String saveOrUpdate(Player player, ModelMap modelMap){
        return "playerForm";
    }

    @GetMapping("/{pageNumber}/page")
    public String getPlayers(@PathVariable int pageNumber){
        return "";
    }

    @GetMapping("/{playerId}")
    public String getPlayerById(@PathVariable Long playerId, ModelMap modelMap){
        return "";
    }

    @GetMapping("/{playerId}")
    public String getPlayerByName(@PathVariable Long playerId, ModelMap modelMap){
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
