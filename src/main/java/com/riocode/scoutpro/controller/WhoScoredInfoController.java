package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.service.WhoScoredInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WhoScoredInfoController {

    private final WhoScoredInfoService wsInfoService;

    public WhoScoredInfoController(WhoScoredInfoService wsInfoService) {
        this.wsInfoService = wsInfoService;
    }

    @GetMapping("/ws/{wsInfoId}")
    public String update(@PathVariable Long wsInfoId){
        return "";
    }

}
