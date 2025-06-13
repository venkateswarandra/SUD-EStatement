package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sud.life.estatement.reports.entity.MaturityLetterData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaturityLetterDTO {
    @JsonProperty("status")
    private String status;

    @JsonProperty("maturityLetterData")
    private List<MaturityLetterData> maturityLetterData;
}
