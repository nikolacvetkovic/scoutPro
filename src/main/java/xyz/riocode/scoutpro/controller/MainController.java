package xyz.riocode.scoutpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/dashboard"})
    public String showDashboard(){
        return "player/dashboard";
    }

    @GetMapping("/compare")
    public String compare(){
        return "player/compare";
    }

    @GetMapping("/transfersandnews")
    public String showTransfersAndNews(){
        return "player/transfersAndNews";
    }

}
