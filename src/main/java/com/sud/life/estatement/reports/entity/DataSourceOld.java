package com.sud.life.estatement.reports.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class DataSourceOld {


    @JsonProperty("status")
    private String status;

    @JsonProperty("annualUnitStatement")
    private AnnualUnitStatementOld annualUnitStatement;

}
