package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FundSummary {
    @JsonProperty("Swach Bharat Cess")
    private String swachBharatCess;

    @JsonProperty("Service Tax")
    private String serviceTax;

    @JsonProperty("ClosingBalanceinUnits")
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

    @JsonProperty("FundValueClosingbalinRs")
    private String fundValueClosingBalInRs;
}
