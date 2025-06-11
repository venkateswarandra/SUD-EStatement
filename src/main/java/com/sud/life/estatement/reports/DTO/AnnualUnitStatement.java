package com.sud.life.estatement.reports.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class AnnualUnitStatement {
    private UnitStatementHeaderBeanDTO unitStatementHeaderBean;
    private List<UnitStatementDetailsBeanDTO> unitStatementDetailsBean;
    private List<FundDetailsDTO> fundDetails;
    private FundSummaryDTO fundSummary;
}
