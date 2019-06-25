package xyz.riocode.scoutpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.riocode.scoutpro.service.MarketValueService;

@Controller
@RequestMapping("/player/{playerId}")
public class MarketValueController {

    private final MarketValueService marketValueService;

    public MarketValueController(MarketValueService marketValueService) {
        this.marketValueService = marketValueService;
    }

    @GetMapping("/marketvalue")
    public String checkForNewMarketValue(@PathVariable Long playerId){
        return "";
    }

}
