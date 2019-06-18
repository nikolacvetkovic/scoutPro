package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.service.MarketValueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
