package com.sud.life.estatement.reports.services;


import com.sud.life.estatement.reports.DTO.CommissionStatementDTO;
import com.sud.life.estatement.reports.entity.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommissionStatementService {

    public CommissionStatementDTO getCommissionStatement() {
        // Create Member Details Commission
        MemberDetailsCommission memberDetails = new MemberDetailsCommission();
        memberDetails.setAgentName("Mr Mahesh Pol");
        memberDetails.setAddress1("A/P Murshdpur");
        memberDetails.setAddress2("Tal-Ashti Beed");
        memberDetails.setAddress4("");
        memberDetails.setAddress5("Maharashtra");
        memberDetails.setPincode("413207");
        memberDetails.setAdvisorCode("80004266");
        memberDetails.setManagerCode("80005497");
        memberDetails.setBankAccNumber("541302010009234");
        memberDetails.setBankName("UNION BANK OF INDIA");
        memberDetails.setLicenseNumber("SUD 8999210");
        memberDetails.setExpiryDate("10/02/2015");
        memberDetails.setPanNumber("AONPP9725Q");
        memberDetails.setPhoneNumber("");
        memberDetails.setRiInternet("");
        memberDetails.setZifsccd("");
        memberDetails.setCampaign("T0801");
        memberDetails.setItemDesc("PUNE");

        // Create Summary Commission
        SummaryCommission summaryCommission = new SummaryCommission();
        summaryCommission.setNewBusiness(new BigDecimal("20800.00"));
        summaryCommission.setRenewal(new BigDecimal("0.00"));
        summaryCommission.setClawBack(new BigDecimal("-7200.00"));
        summaryCommission.setOtherAdjacement(new BigDecimal("0.00"));
        summaryCommission.setTds(new BigDecimal("0.00"));
        summaryCommission.setGrossTotalEarning(new BigDecimal("13600.00"));
        summaryCommission.setNetTotalEarning(new BigDecimal("13600.00"));
        summaryCommission.setTopup(new BigDecimal("0.00"));

        // Create New Business Commissions
        List<NewBusinessCommission> newBusinessCommissions = createNewBusinessCommissions();

        // Create Claw Back Commission
        ClawBackCommission clawBackCommission = new ClawBackCommission();
        clawBackCommission.setCommAccuralDate("30/03/13");
        clawBackCommission.setPolicyIssuanceDate("01/01/00");
        clawBackCommission.setPolicyNo("31293977          ");
        clawBackCommission.setPolicyHolderName("Mr Govind Naraindas Alamchandani");
        clawBackCommission.setProductCode("U09");
        clawBackCommission.setProductName("Dhan Suraksha 3");
        clawBackCommission.setFrequency("01");
        clawBackCommission.setTerm("5");
        clawBackCommission.setPremiumAmount(new BigDecimal("90000.00"));
        clawBackCommission.setCommissionAmount(new BigDecimal("-7200.00"));
        clawBackCommission.setTransactionDetails("Alter From Inception");
        clawBackCommission.setCommissionType("First Year");

        // Create Commission Statement
        CommissionStatement commissionStatement = new CommissionStatement();
        commissionStatement.setMemberDetailsCommissions(Arrays.asList(memberDetails));
        commissionStatement.setSummaryCommissions(Arrays.asList(summaryCommission));
        commissionStatement.setCommissionAdjacentTransactions(new ArrayList<>());
        commissionStatement.setNewBusinessCommissions(newBusinessCommissions);
        commissionStatement.setRenewalCommissions(new ArrayList<>());
        commissionStatement.setTopupCommissions(new ArrayList<>());
        commissionStatement.setClawBackCommissions(Arrays.asList(clawBackCommission));

        // Create and return Commission Response
        return new CommissionStatementDTO("1", commissionStatement);
    }

    private List<NewBusinessCommission> createNewBusinessCommissions() {
        List<NewBusinessCommission> commissions = new ArrayList<>();

        // First commission
        NewBusinessCommission commission1 = new NewBusinessCommission();
        commission1.setCommAccuralDate("30/03/13");
        commission1.setPolicyIssuanceDate("01/01/00");
        commission1.setPolicyNo("31293977          ");
        commission1.setPolicyHolderName("Mr Govind Naraindas Alamchandani");
        commission1.setProductCode("U09");
        commission1.setProductName("Dhan Suraksha 3");
        commission1.setFrequency("01");
        commission1.setTerm("5");
        commission1.setPremiumAmount(new BigDecimal("90000.00"));
        commission1.setCommissionAmount(new BigDecimal("7200.00"));
        commission1.setTransactionDetails("Contract Issue");
        commission1.setCommissionType("First Year");

        // Second commission
        NewBusinessCommission commission2 = new NewBusinessCommission();
        commission2.setCommAccuralDate("05/03/13");
        commission2.setPolicyIssuanceDate("05/03/13");
        commission2.setPolicyNo("31761137          ");
        commission2.setPolicyHolderName("Mrs Arkita Sanjay Alamchandani");
        commission2.setProductCode("U09");
        commission2.setProductName("Dhan Suraksha 3");
        commission2.setFrequency("01");
        commission2.setTerm("5");
        commission2.setPremiumAmount(new BigDecimal("80000.00"));
        commission2.setCommissionAmount(new BigDecimal("6400.00"));
        commission2.setTransactionDetails("Contract Issue");
        commission2.setCommissionType("New Business");

        // Third commission
        NewBusinessCommission commission3 = new NewBusinessCommission();
        commission3.setCommAccuralDate("31/03/13");
        commission3.setPolicyIssuanceDate("31/03/13");
        commission3.setPolicyNo("31761712          ");
        commission3.setPolicyHolderName("Mr Govind Naraindas Alamchandani");
        commission3.setProductCode("U09");
        commission3.setProductName("Dhan Suraksha 3");
        commission3.setFrequency("01");
        commission3.setTerm("5");
        commission3.setPremiumAmount(new BigDecimal("90000.00"));
        commission3.setCommissionAmount(new BigDecimal("7200.00"));
        commission3.setTransactionDetails("Contract Issue");
        commission3.setCommissionType("New Business");

        commissions.add(commission1);
        commissions.add(commission2);
        commissions.add(commission3);

        return commissions;
    }



}
