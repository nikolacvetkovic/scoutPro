package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
