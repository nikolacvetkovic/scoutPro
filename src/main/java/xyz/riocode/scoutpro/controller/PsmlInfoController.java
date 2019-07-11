package xyz.riocode.scoutpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.riocode.scoutpro.service.PsmlInfoService;

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
