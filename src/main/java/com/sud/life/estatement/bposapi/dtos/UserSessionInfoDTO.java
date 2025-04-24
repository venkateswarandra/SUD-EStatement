package com.sud.life.estatement.bposapi.dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSessionInfoDTO {
    private UserDetailsDTO userDetails;
    private UserSessionLog userSessionLog;
    private Boolean isTokenValid;
    private String userid;
    private String status;
    private String messageCode;
    private String message;
}

