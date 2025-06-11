package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AnnualUnitStatementRequestDTO {
    @JsonProperty("policy_no")
    private String policyNo;
    @JsonProperty("financial_year")
    private String financialYear;
}
