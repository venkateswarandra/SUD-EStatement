package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnualUnitStatementOld {

    @JsonProperty("unitStatementHeaderBean")
    private UnitStatementHeader unitStatementHeader;

    @JsonProperty("unitStatementDetailsBean")
    private List<UnitStatementDetails> unitStatementDetails;

    @JsonProperty("fundDetails")
    private List<FundDetailsOld> fundDetails;

    @JsonProperty("fundSummary")
    private FundSummary fundSummary;

}
