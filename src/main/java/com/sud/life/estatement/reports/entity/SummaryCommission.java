package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryCommission {

    @JsonProperty("newBusiness")
    private BigDecimal newBusiness;

    @JsonProperty("renewal")
    private BigDecimal renewal;

    @JsonProperty("clawBack")
    private BigDecimal clawBack;

    @JsonProperty("otherAdjacement")
    private BigDecimal otherAdjacement;

    @JsonProperty("tds")
    private BigDecimal tds;

    @JsonProperty("grossTotalEarning")
    private BigDecimal grossTotalEarning;

    @JsonProperty("netTotalEarning")
    private BigDecimal netTotalEarning;

    @JsonProperty("topup")
    private BigDecimal topup;

}
