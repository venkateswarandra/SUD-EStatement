package com.sud.life.estatement.bposapi.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSessionLog {
    private Long logId;
    private String userLoginId;
    private String accessToken;
    private String refreshToken;
    private String ipAddress;
    private String systemInfo;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private LocalDateTime expiryTime;
    private boolean isExpired;
}