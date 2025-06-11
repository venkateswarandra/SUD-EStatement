package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualUnitStatementDTO {

    @JsonProperty("unitStatementHeaderBean")
    private UnitStatementHeaderBeanDTO unitStatementHeaderBean;

    @JsonProperty("fundDetails")
    private List<FundDetailsDTO> fundDetails;

    @JsonProperty("fundSummary")
    private FundSummaryDTO fundSummary;

    private int status;
}
