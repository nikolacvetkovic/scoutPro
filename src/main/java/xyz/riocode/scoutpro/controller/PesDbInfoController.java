package xyz.riocode.scoutpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.riocode.scoutpro.service.PesDbInfoService;

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
