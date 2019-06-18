package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.service.TransfermarktInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
