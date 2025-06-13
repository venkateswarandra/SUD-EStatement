package com.sud.life.estatement.reports.controllers;

import com.sud.life.estatement.reports.DTO.ProposalDepositeRecieptDTO;
import com.sud.life.estatement.reports.services.ProposalDepositeRecieptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proposal-deposit")
public class ProposalDepositeRecieptController {

    private final ProposalDepositeRecieptService service;

    public ProposalDepositeRecieptController(ProposalDepositeRecieptService service) {
        this.service = service;
    }

    @GetMapping
    public ProposalDepositeRecieptDTO getProposalDeposit() {
        return service.getProposalDepositResponse();
    }

}
