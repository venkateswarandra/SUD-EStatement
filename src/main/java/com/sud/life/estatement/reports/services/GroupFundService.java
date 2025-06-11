package com.sud.life.estatement.reports.services;

import com.sud.life.estatement.reports.DTO.GroupFundDTO;
import org.springframework.stereotype.Service;

@Service
public class GroupFundService {
    public GroupFundDTO fetchPolicyDetails() {
        // Hardcoded values based on the fields of PolicyDetailsDTO
        GroupFundDTO policyDetails = new GroupFundDTO();
        policyDetails.setMasterPolicyNumber("123456");
        policyDetails.setMasterPolicyHoldername("John Doe");
        policyDetails.setPlan("Plan A");
        policyDetails.setStmtPeriod("2024");
        policyDetails.setIntrestRate("5%");
        policyDetails.setOpeningFundBalanceOn("2024-01-01");
        policyDetails.setOpeiningFundBalance("10000");
        policyDetails.setContributionDuringYear("1500");
        policyDetails.setBenifitPaymentDuringYear("500");
        policyDetails.setFundsAsOn("2024-12-31");
        policyDetails.setFunds("11500");
        policyDetails.setIntrestEarnedThisYear("500");
        policyDetails.setClosingFundOn("2024-12-31");
        policyDetails.setClosingFund("12000");

        return policyDetails;
    }
}
