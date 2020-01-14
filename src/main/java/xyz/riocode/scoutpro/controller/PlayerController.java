package xyz.riocode.scoutpro.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.riocode.scoutpro.converter.PlayerConverter;
import xyz.riocode.scoutpro.dto.DashboardDTO;
import xyz.riocode.scoutpro.dto.PlayerCompleteDTO;
import xyz.riocode.scoutpro.dto.PlayerDashboardDTO;
import xyz.riocode.scoutpro.dto.PlayerFormDTO;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.service.PlayerService;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Controller
public class PlayerController {
    
    private final PlayerService playerService;
    private final PlayerConverter playerConverter;

    public PlayerController(PlayerService playerService, PlayerConverter playerConverter) {
        this.playerService = playerService;
        this.playerConverter = playerConverter;
    }

    @GetMapping({"/", "/dashboard"})
    public String showDashboard(){
        return "player/dashboard";
    }

    @GetMapping("/player/new")
    public String showPlayerForm(ModelMap modelMap){
        modelMap.addAttribute("player", new PlayerFormDTO());
        return "player/playerForm";
    }

    @GetMapping("/player/{playerId}/edit")
    public String showPlayerFormForEdit(@PathVariable Long playerId, ModelMap modelMap){
        PlayerFormDTO foundPlayer = playerConverter.playerToPlayerFormDTO(playerService.getByIdAndUser(playerId, "cvele"), "cvele");
        modelMap.addAttribute("player", foundPlayer);
        return "player/playerForm";
    }

    @PostMapping("/player")
    public String saveNewPlayerAndAddToUser(@Valid PlayerFormDTO player, BindingResult bindingResult, ModelMap modelMap){
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().map(fieldError -> fieldError.getField() + "-" + fieldError.getRejectedValue()).forEach(log::error);
            modelMap.addAttribute("player", player);
            return "player/playerForm";
        }
        playerService.createOrUpdate(playerConverter.playerFormDTOToPlayer(player), "cvele");
        return "redirect:/dashboard";
    }

    @PostMapping("/player/{playerId}/add")
    public ResponseEntity addExistingPlayerToUser(@PathVariable Long playerId, @RequestParam Boolean isUserPlayer){
        playerService.addExistingPlayerToUser(playerId, isUserPlayer, "cvele");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/player/{pageNumber}/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DashboardDTO> getPlayers(@PathVariable int pageNumber){
        log.info("Get players by page number: {}", pageNumber);
        return new ResponseEntity<>(playerConverter.playersToDashboardDTO(playerService.getByUserPaging("cvele", pageNumber), "cvele"), HttpStatus.OK);
    }

    @GetMapping(value = "/player/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId, ModelMap modelMap){
        return new ResponseEntity<>(playerService.getByIdAndUser(playerId, "cvele"), HttpStatus.OK);
    }

    @GetMapping("/player/{playerId}/show")
    public String show(@PathVariable Long playerId, ModelMap modelMap){
        PlayerCompleteDTO foundPlayer = playerConverter.playerToPlayerCompleteDTO(playerService.getByIdAndUserComplete(playerId, "cvele"), "cvele");
        modelMap.addAttribute("player", foundPlayer);
        return "player/showPlayer";
    }

    @GetMapping("/player/unfollowed/{playerName}/name")
    public ResponseEntity<List<PlayerDashboardDTO>> getPlayerByNameUnfollowed(@PathVariable String playerName){
        return new ResponseEntity<List<PlayerDashboardDTO>>(playerConverter.playersToPlayerDashboardDTO(playerService.getByNameAndUserUnfollowed(playerName, "cvele"), "cvele"), HttpStatus.OK);
    }

    @GetMapping("/player/{playerId}/compare")
    public String compare(@PathVariable Long playerId, ModelMap modelMap){
        return "";
    }

    @GetMapping("player/compare")
    public String compare(){
        return "player/compare";
    }

    @GetMapping("player/transfersandnews")
    public String showTransfersAndNews(){
        return "player/transfersAndNews";
    }

    @GetMapping("/player/{playerId}/delete")
    public String delete(@PathVariable Long playerId){
        return "";
    }
}
