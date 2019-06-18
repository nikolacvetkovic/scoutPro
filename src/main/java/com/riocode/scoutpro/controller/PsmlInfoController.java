package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.service.PsmlInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PsmlInfoController {

    private final PsmlInfoService psmlInfoService;

    public PsmlInfoController(PsmlInfoService psmlInfoService) {
        this.psmlInfoService = psmlInfoService;
    }

    @GetMapping("/psml/{psmlInfoId}")
    public String update(@PathVariable Long psmlInfoId){
        return "";
    }
}
