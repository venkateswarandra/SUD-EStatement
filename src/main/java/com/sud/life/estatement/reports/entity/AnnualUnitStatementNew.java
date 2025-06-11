package com.sud.life.estatement.reports.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnualUnitStatementNew {

    @JsonProperty("unitStatementHeaderBean")
    private UnitStatementHeader unitStatementHeader;

    @JsonProperty("fundDetails")
    private List<FundDetailsNew> fundDetails;

    @JsonProperty("fundSummary")
    private FundSummary fundSummary;

}
