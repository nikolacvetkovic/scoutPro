package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.service.PesDbInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PesDbInfoController {

    private final PesDbInfoService pesDbInfoService;

    public PesDbInfoController(PesDbInfoService pesDbInfoService) {
        this.pesDbInfoService = pesDbInfoService;
    }

    @GetMapping("/pesdb/{pesDbInfoId}")
    public String update(@PathVariable Long pesDbInfoId){
        return "";
    }

}
