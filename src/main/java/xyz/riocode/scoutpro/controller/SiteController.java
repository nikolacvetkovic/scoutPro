package xyz.riocode.scoutpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.riocode.scoutpro.service.PlayerService;

/**
 *
 * @author Nikola Cvetkovic
 */

@Controller
public class SiteController {

    private final PlayerService playerService;

    public SiteController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping({"/", "/dashboard"})
    public String showDashboard(){
        
        return "dashboard";
    }

    @GetMapping("/compare")
    public String compare(){
        return "compare";
    }

    @GetMapping("/transfersandnews")
    public String showTransfersAndNews(){

        return "transfersAndNews";
    }
}
