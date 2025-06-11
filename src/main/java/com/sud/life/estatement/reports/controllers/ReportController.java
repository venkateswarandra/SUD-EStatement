package com.sud.life.estatement.reports.controllers;

import com.sud.life.estatement.bposapi.dtos.APIResponse;
import com.sud.life.estatement.reports.DTO.*;
import com.sud.life.estatement.reports.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sud/estatement")
public class ReportController {
    @Autowired
    private EstatementExApiServices estatementExApiServices;




    @Autowired
    JBossHomeDirFetcher jBossHomeDirFetcher;


    /*@GetMapping("/path")
    public ResponseEntity<?> fetchPathService() {
        log.debug("Class StudentController :: fetchPathService Method executed");
        try {
            JBossHomeDirFetcher.ReportPaths paths = jBossHomeDirFetcher.FetchPathService();
            log.info("Fetched JBoss JRXML path: {}", paths.getJrxmlPath());
            log.info("Fetched JBoss Image path: {}", paths.getImagePath());
            return new ResponseEntity<>(paths, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching JBoss paths", e);
            return new ResponseEntity<>("Failed to fetch JBoss paths", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /*@PostMapping("/groupFundGenerateReport")
    public ResponseEntity<APIResponse<String>> generateReport(@RequestBody GroupFundDTO policyRequestDTO) {
        try {
            // Call service and return response
            return reportService.generateReportFromAbsolutePath(policyRequestDTO);
        } catch (Exception e) {
            APIResponse<String> errorResponse = APIResponse.<String>builder()
                    .status(500)
                    .message("Failed to generate report: " + e.getMessage())
                    .data(null)
                    .build();
            return ResponseEntity.status(500).body(errorResponse);
        }


    }*/

   @PostMapping("/generateAnnualUnitStatement")
    public ResponseEntity<APIResponse<String>> generateAndReturnReport(@RequestBody AnnualUnitStatementRequestDTO requestDTO) {
        return estatementExApiServices.generateAnnualUnitStatementReport(requestDTO);
    }
    @PostMapping("/ProposalDepositeData")
    public  ResponseEntity<APIResponse<String>> getProposalData(@RequestBody ProposalDepositRequestDTO requestDTO)  {
        return estatementExApiServices.getProposalDepositData(requestDTO);
    }
   /* @PostMapping("/getOrphanPolicyStatement")
    public ResponseEntity<APIResponse<String>> getOrphanPolicy(@RequestBody OrphanPolicyRequestDTO request) {
        return reportService.getOrphanPolicies(request);
    }*/
}
