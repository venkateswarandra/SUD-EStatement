package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sud.life.estatement.reports.entity.CommissionStatement;
import com.sud.life.estatement.reports.entity.UnitStatementHeader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommissionStatementDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("commissionStatement")
    private CommissionStatement commissionStatement;;
}
