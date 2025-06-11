package com.sud.life.estatement.reports.DTO;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrphanPolicyRequestDTO {
    private String agent_no;
    private String startdt;
    private String enddt;

    // Getters and Setters
}