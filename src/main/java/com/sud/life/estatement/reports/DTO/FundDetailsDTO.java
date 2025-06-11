package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundDetailsDTO {
    @JsonProperty("vFund")
    private String vFund;
    @JsonProperty("vFundDesc")
    private String vFundDesc;
    @JsonProperty("value")
    private String value;
    @JsonProperty("unitStatementDetailsBean")
    private List<UnitStatementDetailsBeanDTO> unitStatementDetailsBean;
}
