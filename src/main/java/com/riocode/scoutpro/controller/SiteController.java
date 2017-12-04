package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.service.PlayerService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nikola Cvetkovic
 */

@Controller
@RequestMapping("/")
public class SiteController {
    
    @Autowired
    private PlayerService playerService;
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public String create(Player player, ModelMap model) throws IOException{
        
        model.addAttribute("player", playerService.create(player));
        return "index";
    }
    
    @RequestMapping("/player/{id}")
    public String player(@PathVariable int id, ModelMap model){
        
        model.addAttribute("player", playerService.getById(id));
        return "index";
    }
    
    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
    public String player(@PathVariable int id){
        
        playerService.delete(id);
        return "index";
    }
}
