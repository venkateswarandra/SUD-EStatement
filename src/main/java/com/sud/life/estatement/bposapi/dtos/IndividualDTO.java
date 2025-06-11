package com.sud.life.estatement.bposapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IndividualDTO {
    @JsonProperty("policy_no")
    private String policyNo;
    @JsonProperty("financial_year")
    private String financialYear;
    private String startdt;
    private String enddt;
    @JsonProperty("agent_no")
    private String agentNumber;
    private String month;
    private String year;


}
