package com.sud.life.estatement.reports.controllers;

import com.sud.life.estatement.reports.DTO.MaturityLetterDTO;
import com.sud.life.estatement.reports.services.MaturityLetterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maturity-letter")
public class MaturityLetterController {

    private final MaturityLetterService service;

    public MaturityLetterController(MaturityLetterService service) {
        this.service = service;
    }

    @GetMapping
    public MaturityLetterDTO getMaturityLetter() {
        return service.getMaturityLetterResponse();
    }

}
