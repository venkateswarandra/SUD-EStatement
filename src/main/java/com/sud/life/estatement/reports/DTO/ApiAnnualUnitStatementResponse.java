package com.sud.life.estatement.reports.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiAnnualUnitStatementResponse {
    private String status;
    private AnnualUnitStatement annualUnitStatement;
}
