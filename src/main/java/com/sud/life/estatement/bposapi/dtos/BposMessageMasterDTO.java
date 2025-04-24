package com.sud.life.estatement.bposapi.dtos;

import java.time.LocalDateTime;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BposMessageMasterDTO {

    private Long msgId;

    private String msgType;

    private String msgCode;

    private String msgValue;

    private String moduleCode;

    private Long isActive = 1L;

    private String createdBy;

    private LocalDateTime createdAt;

    private String modifiedBy;

    private LocalDateTime modifiedAt;

    private Long isDeleted = 0L;

    private String moduleName;

    private String activeStatus;

    private String msgTypeName;


}
