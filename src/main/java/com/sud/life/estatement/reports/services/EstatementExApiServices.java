package com.sud.life.estatement.reports.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sud.life.estatement.bposapi.dtos.APIResponse;
import com.sud.life.estatement.bposapi.services.BposApiServices;
import com.sud.life.estatement.reports.DTO.*;
import com.sud.life.estatement.reports.controllers.ReportController;
import com.sud.life.estatement.reports.util.JasperReportUtil;
import com.sud.life.estatement.utils.BposConfigConstants;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;


@Service
@Slf4j
public class EstatementExApiServices {
    private JasperReport jasperReport;

    @Autowired
    private JBossHomeDirFetcher jBossHomeDirFetcher;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private JasperReport cachedReport;

    @Autowired
    private BposApiServices bposApiServices;
    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

/*
    public ResponseEntity<APIResponse<String>> generateAnnualUnitStatementReport(@RequestBody AnnualUnitStatementRequestDTO requestDTO) {
        try {
            if (requestDTO.getPolicyNo() == null || requestDTO.getPolicyNo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message("Policy number should not be null or empty.").data(null).build());
            }
            if (requestDTO.getFinancialYear() == null || requestDTO.getFinancialYear().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message("Financial year should not be null or empty.").data(null).build());
            }
            if (!requestDTO.getFinancialYear().matches("\\d{4}-\\d{4}")) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Financial year should be in format YYYY-YYYY (e.g., 2023-2024).")
                        .data(null)
                        .build());
            }
            String uri = "http://192.168.2.24:8080/exapi/api/getAnnualUnitStatementData";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AnnualUnitStatementRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);

            ResponseEntity<AnnualUnitStatementResponseDTO> response = restTemplate.postForEntity(uri, requestEntity, AnnualUnitStatementResponseDTO.class);
            AnnualUnitStatementResponseDTO responseBody = response.getBody();

            if (responseBody == null || responseBody.getAnnualUnitStatement() == null ||
                    responseBody.getAnnualUnitStatement().getUnitStatementDetailsBean().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder().status(HttpStatus.NOT_FOUND.value()).message("No data available for the given policy number and financial year.").data(null).build());
            }
            JBossHomeDirFetcher.ReportPaths paths = jBossHomeDirFetcher.FetchPathService();
            File jrxmlFile = new File(paths.getJrxmlPath() + "/UnitStmt.jrxml");
            if (!jrxmlFile.exists()) {
                throw new IOException("JRXML file not found: " + jrxmlFile.getPath());
            }

            if (jasperReport == null) {
                jasperReport = JasperCompileManager.compileReport(jrxmlFile.getAbsolutePath());
            }

            String jsonString = objectMapper.writeValueAsString(responseBody);
            String base64 = JasperReportUtil.generateBase64PdfFromJson(jsonString, jasperReport, paths,null);
            return ResponseEntity.ok(APIResponse.<String>builder().status(HttpStatus.OK.value()).message("Proposal deposit report generated successfully.").data(base64).reportFileName("proposal-deposit.pdf").build());
        } catch (IOException | JRException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.<String>builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error generating report: " + e.getMessage()).data(null).build());
        }
    }
*/

    public ResponseEntity<APIResponse<String>> getProposalDepositData(@RequestBody ProposalDepositRequestDTO requestDTO) {
        try {
            if (requestDTO.getPolicy_no() == null || requestDTO.getPolicy_no().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message("Policy number should not be null or empty.").data(null).build());
            }
            String uri = bposApiServices.keys.stream().filter(x -> x.getConfigKey().equals(BposConfigConstants.PROPOSAL_DEPOSITE_RECEIPT.getValue())).findFirst().get().getConfigValue();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<ProposalDepositRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);
            ResponseEntity<ProposalDepositResponseDTO> response = restTemplate.postForEntity(uri, requestEntity, ProposalDepositResponseDTO.class);
            ProposalDepositResponseDTO responseBody = response.getBody();
            if (responseBody == null || responseBody.getProposalDeposite() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder().status(HttpStatus.NOT_FOUND.value()).message("No data available for the given policy number.").data(null).build());
            }
            JBossHomeDirFetcher.ReportPaths paths = jBossHomeDirFetcher.FetchPathService();
            File jrxmlFile = new File(paths.getJrxmlPath() + "/Proposal Deposite Receipt.jrxml");
            if (!jrxmlFile.exists()) {
                throw new IOException("JRXML file not found: " + jrxmlFile.getPath());
            }
            if (jasperReport == null) {
                jasperReport = JasperCompileManager.compileReport(jrxmlFile.getAbsolutePath());
            }
           /* String instAmountStr = responseBody.getProposalDeposite().getInstAmount();
            String amountInWords = "";
            if (instAmountStr != null && !instAmountStr.trim().isEmpty()) {
                long instAmount = (long) Double.parseDouble(instAmountStr);
                amountInWords = NumberToWordsConverter.convert(instAmount);
            }
            responseBody.getProposalDeposite().setInstAmountWord(amountInWords);*/
            String instAmount=responseBody.getProposalDeposite().getInstAmountWord();
            String jsonString = objectMapper.writeValueAsString(responseBody);
            String base64 = JasperReportUtil.generateBase64PdfFromJson(jsonString, jasperReport, paths);
            return ResponseEntity.ok(APIResponse.<String>builder().status(HttpStatus.OK.value()).message("Proposal deposit report generated successfully.").data(base64).reportFileName("proposal-deposit.pdf").build());
        } catch (IOException | JRException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.<String>builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error generating report: " + e.getMessage()).data(null).build());
        }
    }
    /*public ResponseEntity<APIResponse<String>> generateAnnualUnitStatementReport(@RequestBody AnnualUnitStatementRequestDTO requestDTO) {
        try {
            if (requestDTO.getPolicyNo() == null || requestDTO.getPolicyNo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message("Policy number should not be null or empty.").data(null).build());
            }
            if (requestDTO.getFinancialYear() == null || requestDTO.getFinancialYear().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message("Financial year should not be null or empty.").data(null).build());
            }
            if (!requestDTO.getFinancialYear().matches("\\d{4}-\\d{4}")) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Financial year should be in format YYYY-YYYY (e.g., 2023-2024).")
                        .data(null)
                        .build());
            }
            String uri = "http://192.168.2.24:8080/exapi/api/getAnnualUnitStatementData";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AnnualUnitStatementRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);

            ResponseEntity<AnnualUnitStatementResponseDTO> response = restTemplate.postForEntity(uri, requestEntity, AnnualUnitStatementResponseDTO.class);
            AnnualUnitStatementResponseDTO responseBody = response.getBody();

           if (responseBody == null || responseBody.getDataSource() == null ||
                    responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementDetailsBean().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder().status(HttpStatus.NOT_FOUND.value()).message("No data available for the given policy number and financial year.").data(null).build());
            }

            AnnualUnitStatementResponseDTO responseDTO = new AnnualUnitStatementResponseDTO();

            DateRange dateRange = new DateRange();
            String financialYear = requestDTO.getFinancialYear();
            if (financialYear != null && financialYear.matches("\\d{4}-\\d{4}")) {
                String startYear = financialYear.substring(0, 4);
                String endYear = financialYear.substring(5, 9);
                dateRange.setFrom("01-Apr-" + startYear);
                dateRange.setTo("31-Mar-" + endYear);
            } else {
                // Fallback default dates
                dateRange.setFrom("01-Apr-2018");
                dateRange.setTo("31-Mar-2019");
            }
            responseDTO.setDateRange(dateRange);

            // Map DataSource
            DataSource dataSource = new DataSource();
            dataSource.setStatus(responseBody.getDataSource().getStatus());

            AnnualUnitStatement annualUnitStatement = new AnnualUnitStatement();
            annualUnitStatement.setUnitStatementHeaderBean(responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementHeaderBean());
            annualUnitStatement.setFundSummary(responseBody.getDataSource().getAnnualUnitStatement().getFundSummary());

            // Group unitStatementDetailsBean by fund
            List<FundDetailsDTO> fundDetailsList = new ArrayList<>();
            List<UnitStatementDetailsBeanDTO> apiUnitDetails = responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementDetailsBean();

            for (FundDetailsDTO apiFund : responseBody.getDataSource().getAnnualUnitStatement().getFundDetails()) {
                FundDetailsDTO fundDetails = new FundDetailsDTO();
                fundDetails.setVFund(apiFund.getVFund());
                fundDetails.setVFundDesc(apiFund.getVFundDesc());
                fundDetails.setValue(apiFund.getValue());

                List<UnitStatementDetailsBeanDTO> detailsBeans = new ArrayList<>();
                for (UnitStatementDetailsBeanDTO unitDetail : apiUnitDetails) {
                    if (unitDetail.getUnitLinkedFund().equals(apiFund.getVFund())) {
                        detailsBeans.add(unitDetail);
                    }
                }
                fundDetails.setUnitStatementDetailsBean(detailsBeans);
                fundDetailsList.add(fundDetails);
            }

            annualUnitStatement.setFundDetails(fundDetailsList);
            dataSource.setAnnualUnitStatement(annualUnitStatement);
            responseDTO.setDataSource(dataSource);

            // Convert ResponseDTO to JSON for JsonDataSource
            String jsonString = objectMapper.writeValueAsString(responseDTO);

            JBossHomeDirFetcher.ReportPaths paths = jBossHomeDirFetcher.FetchPathService();
            File jrxmlFile = new File(paths.getJrxmlPath() + "/UnitStmt.jrxml");
            if (!jrxmlFile.exists()) {
                throw new IOException("JRXML file not found: " + jrxmlFile.getPath());
            }

            if (jasperReport == null) {
                jasperReport = JasperCompileManager.compileReport(jrxmlFile.getAbsolutePath());
            }

            // Pass transformed JSON to JasperReportUtil
            String base64 = JasperReportUtil.generateBase64PdfFromJson(jsonString, jasperReport, paths, null);
            return ResponseEntity.ok(APIResponse.<String>builder().status(HttpStatus.OK.value()).message("Proposal deposit report generated successfully.").data(base64).reportFileName("proposal-deposit.pdf").build());
        } catch (IOException | JRException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.<String>builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error generating report: " + e.getMessage()).data(null).build());
        }
    }*/
/*
    public ResponseEntity<APIResponse<String>> generateAnnualUnitStatementReport(@RequestBody AnnualUnitStatementRequestDTO requestDTO) {
        try {
            // Validate input
            if (requestDTO.getPolicyNo() == null || requestDTO.getPolicyNo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Policy number should not be null or empty.")
                        .data(null)
                        .build());
            }
            if (requestDTO.getFinancialYear() == null || requestDTO.getFinancialYear().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Financial year should not be null or empty.")
                        .data(null)
                        .build());
            }
            if (!requestDTO.getFinancialYear().matches("\\d{4}-\\d{4}")) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Financial year should be in format YYYY-YYYY (e.g., 2023-2024).")
                        .data(null)
                        .build());
            }

            // Log the request
            System.out.println("Request DTO: " + requestDTO);

            // Call external API
            String uri = "http://192.168.2.24:8080/exapi/api/getAnnualUnitStatementData";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AnnualUnitStatementRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);

            ResponseEntity<AnnualUnitStatementResponseDTO> response = restTemplate.postForEntity(uri, requestEntity, AnnualUnitStatementResponseDTO.class);
            AnnualUnitStatementResponseDTO responseBody = response.getBody();

            // Log the API response
            System.out.println("API Response: " + responseBody);

            // Check for null or empty data
            if (responseBody == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("No response from the API.")
                        .data(null)
                        .build());
            }
            if (responseBody.getDataSource() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("DataSource is null in the API response.")
                        .data(null)
                        .build());
            }
            if (responseBody.getDataSource().getAnnualUnitStatement() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("AnnualUnitStatement is null in the API response.")
                        .data(null)
                        .build());
            }
            if (responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementDetailsBean().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("No unit statement details available for the given policy number and financial year.")
                        .data(null)
                        .build());
            }

            // Create response DTO
            AnnualUnitStatementResponseDTO responseDTO = new AnnualUnitStatementResponseDTO();
            DateRange dateRange = new DateRange();
            String financialYear = requestDTO.getFinancialYear();
            if (financialYear != null && financialYear.matches("\\d{4}-\\d{4}")) {
                String startYear = financialYear.substring(0, 4);
                String endYear = financialYear.substring(5, 9);
                dateRange.setFrom("01-Apr-" + startYear);
                dateRange.setTo("31-Mar-" + endYear);
            } else {
                dateRange.setFrom("01-Apr-2018");
                dateRange.setTo("31-Mar-2019");
            }
            responseDTO.setDateRange(dateRange);

            // Map DataSource
            DataSource dataSource = new DataSource();
            dataSource.setStatus(responseBody.getDataSource().getStatus());

            AnnualUnitStatement annualUnitStatement = new AnnualUnitStatement();
            annualUnitStatement.setUnitStatementHeaderBean(responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementHeaderBean());
            annualUnitStatement.setFundSummary(responseBody.getDataSource().getAnnualUnitStatement().getFundSummary());

            // Fund code mapping
            Map<String, String> fundCodeMapping = new HashMap<>();
            fundCodeMapping.put("BLFD", "BALFD"); // Map BLFD to BALFD
            fundCodeMapping.put("EQFD", "EQFD"); // No change needed for EQFD

            // Group unitStatementDetailsBean by fund
            List<FundDetailsDTO> fundDetailsList = new ArrayList<>();
            List<UnitStatementDetailsBeanDTO> apiUnitDetails = responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementDetailsBean();

            for (FundDetailsDTO apiFund : responseBody.getDataSource().getAnnualUnitStatement().getFundDetails()) {
                FundDetailsDTO fundDetails = new FundDetailsDTO();
                String mappedFundCode = fundCodeMapping.getOrDefault(apiFund.getVFund(), apiFund.getVFund());
                fundDetails.setVFund(mappedFundCode);
                fundDetails.setVFundDesc(apiFund.getVFundDesc());
                fundDetails.setValue(apiFund.getValue());

                List<UnitStatementDetailsBeanDTO> detailsBeans = new ArrayList<>();
                for (UnitStatementDetailsBeanDTO unitDetail : apiUnitDetails) {
                    String mappedUnitFundCode = fundCodeMapping.getOrDefault(unitDetail.getUnitLinkedFund(), unitDetail.getUnitLinkedFund());
                    if (mappedUnitFundCode.equals(mappedFundCode)) {
                        UnitStatementDetailsBeanDTO mappedUnitDetail = new UnitStatementDetailsBeanDTO(unitDetail);
                        mappedUnitDetail.setUnitLinkedFund(mappedFundCode); // Update fund code
                        detailsBeans.add(mappedUnitDetail);
                    }
                }
                fundDetails.setUnitStatementDetailsBean(detailsBeans);
                fundDetailsList.add(fundDetails);
            }

            annualUnitStatement.setFundDetails(fundDetailsList);
            dataSource.setAnnualUnitStatement(annualUnitStatement);
            responseDTO.setDataSource(dataSource);

            // Convert ResponseDTO to JSON
            String jsonString = objectMapper.writeValueAsString(responseDTO);

            // Generate report
            JBossHomeDirFetcher.ReportPaths paths = jBossHomeDirFetcher.FetchPathService();
            File jrxmlFile = new File(paths.getJrxmlPath() + "/UnitStmt.jrxml");
            if (!jrxmlFile.exists()) {
                throw new IOException("JRXML file not found: " + jrxmlFile.getPath());
            }

            if (jasperReport == null) {
                jasperReport = JasperCompileManager.compileReport(jrxmlFile.getAbsolutePath());
            }

            String base64 = JasperReportUtil.generateBase64PdfFromJson(jsonString, jasperReport, paths, null);
            return ResponseEntity.ok(APIResponse.<String>builder()
                    .status(HttpStatus.OK.value())
                    .message("Proposal deposit report generated successfully.")
                    .data(base64)
                    .reportFileName("proposal-deposit.pdf")
                    .build());
        } catch (IOException | JRException e) {
            System.err.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.<String>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Error generating report: " + e.getMessage())
                    .data(null)
                    .build());
        }
    }
*/
    public ResponseEntity<APIResponse<String>> generateAnnualUnitStatementReport(@RequestBody AnnualUnitStatementRequestDTO requestDTO) {
        try {
            // Validate input
            if (requestDTO.getPolicyNo() == null || requestDTO.getPolicyNo().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Policy number should not be null or empty.")
                        .data(null)
                        .build());
            }
            if (requestDTO.getFinancialYear() == null || requestDTO.getFinancialYear().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Financial year should not be null or empty.")
                        .data(null)
                        .build());
            }
            if (!requestDTO.getFinancialYear().matches("\\d{4}-\\d{4}")) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Financial year should be in format YYYY-YYYY (e.g., 2023-2024).")
                        .data(null)
                        .build());
            }

            // Log the request
            logger.info("Received request: {}", requestDTO);

            // Call external API
            String uri = "http://192.168.2.24:8080/exapi/api/getAnnualUnitStatementData";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AnnualUnitStatementRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);

            ResponseEntity<AnnualUnitStatementResponseDTO> response = restTemplate.postForEntity(uri, requestEntity, AnnualUnitStatementResponseDTO.class);
            AnnualUnitStatementResponseDTO responseBody = response.getBody();

            // Log the API response
            logger.info("API response: {}", responseBody);

            // Check for null or empty data
            if (responseBody == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("No response from the API.")
                        .data(null)
                        .build());
            }
            if (responseBody.getDataSource().getAnnualUnitStatement() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("AnnualUnitStatement is null in the API response.")
                        .data(null)
                        .build());
            }
            if (responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementDetailsBean() == null ||
                    responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementDetailsBean().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("No unit statement details available for the given policy number and financial year.")
                        .data(null)
                        .build());
            }

            // Create response DTO
            AnnualUnitStatementResponseDTO responseDTO = new AnnualUnitStatementResponseDTO();
            DateRange dateRange = new DateRange();
            String financialYear = requestDTO.getFinancialYear();
            String startYear = financialYear.substring(0, 4);
            String endYear = financialYear.substring(5, 9);
            dateRange.setFrom("01-Apr-" + startYear);
            dateRange.setTo("31-Mar-" + endYear);
            responseDTO.setDateRange(dateRange);

            // Map DataSource
            DataSource dataSource = new DataSource();
            dataSource.setStatus(responseBody.getDataSource().getStatus());

            AnnualUnitStatement annualUnitStatement = new AnnualUnitStatement();
            annualUnitStatement.setUnitStatementHeaderBean(responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementHeaderBean());
            annualUnitStatement.setFundSummary(responseBody.getDataSource().getAnnualUnitStatement().getFundSummary());

            // Fund code mapping
            Map<String, String> fundCodeMapping = new HashMap<>();
            fundCodeMapping.put("BLFD", "BALFD");
            fundCodeMapping.put("EQFD", "EQFD");

            // Group unitStatementDetailsBean by fund
            List<FundDetailsDTO> fundDetailsList = new ArrayList<>();
            List<UnitStatementDetailsBeanDTO> apiUnitDetails = responseBody.getDataSource().getAnnualUnitStatement().getUnitStatementDetailsBean();

            for (FundDetailsDTO apiFund : responseBody.getDataSource().getAnnualUnitStatement().getFundDetails()) {
                FundDetailsDTO fundDetails = new FundDetailsDTO();
                String mappedFundCode = fundCodeMapping.getOrDefault(apiFund.getVFund(), apiFund.getVFund());
                fundDetails.setVFund(mappedFundCode);
                fundDetails.setVFundDesc(apiFund.getVFundDesc());
                fundDetails.setValue(apiFund.getValue());

                List<UnitStatementDetailsBeanDTO> detailsBeans = new ArrayList<>();
                for (UnitStatementDetailsBeanDTO unitDetail : apiUnitDetails) {
                    String mappedUnitFundCode = fundCodeMapping.getOrDefault(unitDetail.getUnitLinkedFund(), unitDetail.getUnitLinkedFund());
                    if (mappedUnitFundCode.equals(mappedFundCode)) {
                        UnitStatementDetailsBeanDTO mappedUnitDetail = new UnitStatementDetailsBeanDTO(unitDetail);
                        mappedUnitDetail.setUnitLinkedFund(mappedFundCode);
                        detailsBeans.add(mappedUnitDetail);
                    }
                }
                fundDetails.setUnitStatementDetailsBean(detailsBeans);
                fundDetailsList.add(fundDetails);
            }

            annualUnitStatement.setFundDetails(fundDetailsList);
            dataSource.setAnnualUnitStatement(annualUnitStatement);
            responseDTO.setDataSource(dataSource);

            // Convert ResponseDTO to JSON
            String jsonString = objectMapper.writeValueAsString(responseDTO);
            logger.info("Transformed JSON: {}", jsonString);

            // Generate report
            JBossHomeDirFetcher.ReportPaths paths = jBossHomeDirFetcher.FetchPathService();
            File jrxmlFile = new File(paths.getJrxmlPath() + "/UnitStmt.jrxml");
            if (!jrxmlFile.exists()) {
                logger.error("JRXML file not found: {}", jrxmlFile.getPath());
                throw new IOException("JRXML file not found: " + jrxmlFile.getPath());
            }

            synchronized (this) {
                if (jasperReport == null) {
                    jasperReport = JasperCompileManager.compileReport(jrxmlFile.getAbsolutePath());
                    logger.info("JasperReport compiled successfully for UnitStmt.jrxml");
                }
            }

            String base64 = JasperReportUtil.generateBase64PdfFromJson(jsonString, jasperReport, paths);
            return ResponseEntity.ok(APIResponse.<String>builder()
                    .status(HttpStatus.OK.value())
                    .message("Annual unit statement report generated successfully.")
                    .data(base64)
                    .reportFileName("annual-unit-statement.pdf")
                    .build());

        } catch (RestClientException e) {
            logger.error("API call failed: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(APIResponse.<String>builder()
                    .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                    .message("Failed to fetch data from external API: " + e.getMessage())
                    .data(null)
                    .build());
        } catch (IOException | JRException e) {
            logger.error("Error generating report: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.<String>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Error generating report: " + e.getMessage())
                    .data(null)
                    .build());
        }
    }

   /* public ResponseEntity<APIResponse<String>> getOrphanPolicies(@RequestBody OrphanPolicyRequestDTO requestDTO) {
        try {
            if (requestDTO.getAgent_no() == null || requestDTO.getAgent_no().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(APIResponse.<String>builder().status(HttpStatus.BAD_REQUEST.value()).message("agent number should not be null or empty.").data(null).build());
            }
            String uri = bposApiServices.keys.stream().filter(x -> x.getConfigKey().equals(BposConfigConstants.ORPHAN_POLICY_RECEIPT.getValue())).findFirst().get().getConfigValue();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<OrphanPolicyRequestDTO> requestEntity = new HttpEntity<>(requestDTO, headers);
            ResponseEntity<OrphanPolicyResponseDTO> response = restTemplate.postForEntity(uri, requestEntity, OrphanPolicyResponseDTO.class);
            OrphanPolicyResponseDTO responseBody = response.getBody();
            if (responseBody == null || responseBody.getOrphanPolicy() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.<String>builder().status(HttpStatus.NOT_FOUND.value()).message("No data available for the given policy number.").data(null).build());
            }
            JBossHomeDirFetcher.ReportPaths paths = jBossHomeDirFetcher.FetchPathService();
            File jrxmlFile = new File(paths.getJrxmlPath() + "/Proposal Deposite Receipt.jrxml");
            if (!jrxmlFile.exists()) {
                throw new IOException("JRXML file not found: " + jrxmlFile.getPath());
            }
            if (jasperReport == null) {
                jasperReport = JasperCompileManager.compileReport(jrxmlFile.getAbsolutePath());
            }
           *//* String instAmountStr = responseBody.getOrphanPolicy().getInstAmount();
            String amountInWords = "";
            if (instAmountStr != null && !instAmountStr.trim().isEmpty()) {
                long instAmount = (long) Double.parseDouble(instAmountStr);
                amountInWords = NumberToWordsConverter.convert(instAmount);
            }
            responseBody.getProposalDeposite().setInstAmountWord(amountInWords);
            String instAmount=responseBody.getProposalDeposite().getInstAmountWord();
            String jsonString = objectMapper.writeValueAsString(responseBody);*//*
            String base64 = JasperReportUtil.generateBase64PdfFromJson(jsonString, jasperReport, paths);
            return ResponseEntity.ok(APIResponse.<String>builder().status(HttpStatus.OK.value()).message("Proposal deposit report generated successfully.").data(base64).reportFileName("proposal-deposit.pdf").build());
        } catch (IOException | JRException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.<String>builder().status(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Error generating report: " + e.getMessage()).data(null).build());
        }
    }*/
}