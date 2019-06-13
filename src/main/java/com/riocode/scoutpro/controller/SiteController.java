package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nikola Cvetkovic
 */

@Controller
@RequestMapping("/")
public class SiteController {
    
    //@Autowired
    private PlayerService playerService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        modelMap.addAttribute("players", playerService.getAll());
        
        return "index";
    }
    
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(){
        
        return "dashboard";
    }
    
    @RequestMapping(value = "/addplayer", method = RequestMethod.GET)
    public String addplayer(){
        
        return "addplayer";
    }
    
    @RequestMapping(value = "/compare", method = RequestMethod.GET)
    public String compare(){
        
        return "compare";
    }
    
    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    public String compareResult(){
        
        return "compareResult";
    }
}
