package com.riocode.scoutpro.controller;

import com.riocode.scoutpro.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player/{playerId}")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/transfer")
    public String checkForNewTransfer(@PathVariable Long playerId){
        return "";
    }
}
