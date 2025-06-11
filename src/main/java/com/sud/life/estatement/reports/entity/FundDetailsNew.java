package com.sud.life.estatement.reports.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundDetailsNew {

    @JsonProperty("vFund")
    private String vFund;

    @JsonProperty("vFundDesc")
    private String vFundDesc;

    @JsonProperty("value")
    private String value;

    @JsonProperty("unitStatementDetailsBean")
    private List<UnitStatementDetails> unitStatementDetailsBean;

}
