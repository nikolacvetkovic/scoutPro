package xyz.riocode.scoutpro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import xyz.riocode.scoutpro.converter.PlayerConverter;
import xyz.riocode.scoutpro.dto.PlayerDashboardDTO;
import xyz.riocode.scoutpro.dto.PlayerFormDTO;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.service.PlayerService;

import javax.validation.Valid;
import java.util.Set;

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

    @GetMapping("player/new")
    public String showPlayerForm(ModelMap modelMap){
        modelMap.addAttribute("player", new PlayerFormDTO());
        return "player/playerForm";
    }

    @PostMapping("player/new")
    public String save(@Valid PlayerFormDTO player, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "playerForm";
        playerService.create(playerConverter.playerFormDTOToPlayer(player, "cvele"), "cvele");
        return "redirect:/dashboard";
    }

    @GetMapping(value = "player/{pageNumber}/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<PlayerDashboardDTO>> getPlayers(@PathVariable int pageNumber){
        return new ResponseEntity<>(playerConverter.playerToPlayerDashboardDTO(playerService.getByUserPaging("cvele", pageNumber), "cvele"), HttpStatus.OK);
    }

    @GetMapping("player/{playerId}/edit")
    public String showPlayerFormForEdit(@PathVariable Long playerId, ModelMap modelMap){
        PlayerFormDTO foundPlayer = playerConverter.playerToPlayerFormDTO(playerService.getByIdAndUser(playerId, "cvele"), "cvele");
        modelMap.addAttribute("player", foundPlayer);
        return "player/playerForm";
    }

    @PostMapping("player/edit")
    public String update(@Valid PlayerFormDTO playerFormDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "playerForm";
        playerService.update(playerConverter.playerFormDTOToPlayer(playerFormDTO, "cvele"), "cvele");
        return "redirect:/dashboard";
    }

    @GetMapping(value = "player/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Player> getPlayerById(@PathVariable Long playerId, ModelMap modelMap){
        return new ResponseEntity<>(playerService.getByIdAndUser(playerId, "cvele"), HttpStatus.OK);
    }

    @GetMapping("player/{playerId}/show")
    public String show(@PathVariable Long playerId, ModelMap modelMap){
        return "";
    }

    @GetMapping("player/{playerName}/name")
    public String getPlayerByName(@PathVariable Long playerName, ModelMap modelMap){
        return "";
    }

    @GetMapping("player/{playerId}/compare")
    public String compare(@PathVariable Long playerId, ModelMap modelMap){
        return "player/compare";
    }

    @GetMapping("player/compare")
    public String compare(){
        return "player/compare";
    }

    @GetMapping("player/transfersandnews")
    public String showTransfersAndNews(){
        return "player/transfersAndNews";
    }

    @GetMapping("player/{playerId}/delete")
    public String delete(@PathVariable Long playerId){
        return "";
    }
}
