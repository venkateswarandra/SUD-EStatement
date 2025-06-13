package com.sud.life.estatement.reports.controllers;



import com.sud.life.estatement.reports.DTO.CommissionStatementDTO;
import com.sud.life.estatement.reports.services.CommissionStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/commission")
@CrossOrigin(origins = "*")
public class CommissionStatementController {
    @Autowired
    private CommissionStatementService   commissionService;

    /**
     * Get complete commission statement
     * @return CommissionStatementDTO with all commission details
     */
    @GetMapping("/statement")
    public ResponseEntity<CommissionStatementDTO> getCommissionStatement() {
        try {
            CommissionStatementDTO response = commissionService.getCommissionStatement();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
