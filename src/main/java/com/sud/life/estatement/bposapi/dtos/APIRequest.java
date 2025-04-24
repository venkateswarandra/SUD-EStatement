package com.sud.life.estatement.bposapi.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIRequest<T> {
    private int status;
    private String messageCode;
    private String messageType;
    private String message;
    private T data;
    private Long totalDataSize;
    private String exceptionMessage;
    private List<ValidationError> errors;
    private String reportFileName;

    @Data
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        private final String field;
        private final String message;
    }
}
