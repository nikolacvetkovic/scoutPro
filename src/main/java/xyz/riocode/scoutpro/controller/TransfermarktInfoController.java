package xyz.riocode.scoutpro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.riocode.scoutpro.service.TransfermarktInfoService;

@Controller
public class TransfermarktInfoController {

    private final TransfermarktInfoService tmInfoService;

    public TransfermarktInfoController(TransfermarktInfoService tmInfoService) {
        this.tmInfoService = tmInfoService;
    }

    @GetMapping("/tm/{tmInfoId}")
    public String update(@PathVariable Long tmInfoId){
        return "";
    }
}
