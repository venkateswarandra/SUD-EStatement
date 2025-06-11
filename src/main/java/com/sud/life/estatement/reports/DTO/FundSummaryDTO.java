package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundSummaryDTO {
    @JsonProperty("Swach Bharat Cess")
    private String swachBharatCess;
    @JsonProperty("Service Tax")
    private String serviceTax;
    @JsonProperty("Closing Balance (in Units)")
    private String closingBalanceInUnits;
    @JsonProperty("Contract Fee")
    private String contractFee;
    @JsonProperty("NAV at End of Chosen Period")
    private String navAtEndOfChosenPeriod;
    @JsonProperty("Funds Allocated")
    private String fundsAllocated;
    @JsonProperty("Fund NameSFIN")
    private String fundNameSFIN;
    @JsonProperty("Accidental Death and TPD")
    private String accidentalDeathAndTPD;
    @JsonProperty("Fund Value-Closing bal (in Rs.)")
    private String fundValueClosingBalInRs;
}
