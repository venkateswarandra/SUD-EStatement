package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnualUnitStatementResponseDTO {
    @JsonProperty("DateRange")
    private DateRange dateRange;

    @JsonProperty("DataSource")
    private DataSource dataSource;
}
