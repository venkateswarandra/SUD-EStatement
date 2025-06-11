package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DateRange {
    @JsonProperty("From")
    private String from;

    @JsonProperty("To")
    private String to;
}
