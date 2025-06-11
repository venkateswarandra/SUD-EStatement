package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundDetailsOld {
    @JsonProperty("vFund")
    private String vFund;

    @JsonProperty("vFundDesc")
    private String vFundDesc;

    @JsonProperty("value")
    private String value;
}
