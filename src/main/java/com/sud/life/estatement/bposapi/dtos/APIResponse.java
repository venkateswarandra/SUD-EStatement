package com.sud.life.estatement.bposapi.dtos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponse<T> {
    private int status;
    private String message;
    private T data;
    private String reportFileName;
}

