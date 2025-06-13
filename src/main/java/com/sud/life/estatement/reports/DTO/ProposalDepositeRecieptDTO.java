package com.sud.life.estatement.reports.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sud.life.estatement.reports.entity.ProposalDeposit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProposalDepositeRecieptDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("status")
    private ProposalDeposit proposalDeposite;
}
