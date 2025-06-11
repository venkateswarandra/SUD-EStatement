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
public class BposConfigMasterDTO {
    private Long configId;

    private String configKey;

    private String configValue;

    private String configType;

    private String moduleCode;

    private Long isActive = 1L;

    private String createdBy;

    private LocalDateTime createdAt;

    private String modifiedBy;

    private LocalDateTime modifiedAt;

    private Long isDeleted = 0L;

    private String moduleName;

    private String activeStatus;



}
