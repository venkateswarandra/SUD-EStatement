package com.sud.life.estatement.reports.DTO;

import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrphanPolicyResponseDTO {
    private String status;
    private List<OrphanPolicyDTO> orphanPolicy;
}

