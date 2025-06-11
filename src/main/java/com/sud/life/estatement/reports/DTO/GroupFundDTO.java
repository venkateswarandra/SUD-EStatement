package com.sud.life.estatement.reports.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupFundDTO {
    private String masterPolicyNumber;
    private String masterPolicyHoldername;
    private String plan;
    private String stmtPeriod;
    private String intrestRate;
    private String openingFundBalanceOn;
    private String opeiningFundBalance;
    private String contributionDuringYear;
    private String benifitPaymentDuringYear;
    private String fundsAsOn;
    private String funds;
    private String intrestEarnedThisYear;
    private String closingFundOn;
    private String closingFund;
}
