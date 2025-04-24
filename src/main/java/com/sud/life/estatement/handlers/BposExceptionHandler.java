package com.sud.life.estatement.handlers;


import com.sud.life.estatement.exceptions.BposBusinessException;
import com.sud.life.estatement.bposapi.dtos.APIRequest;
import com.sud.life.estatement.bposapi.services.BposApiServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class BposExceptionHandler {
    @Autowired
    private BposApiServices bposApiServices;

    @ExceptionHandler(BposBusinessException.class)
    public ResponseEntity<APIRequest<Void>> handleBposBusinessException(BposBusinessException exception) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = exception.getMessage();
        if (Objects.isNull(message) || Objects.equals(message, ""))
            message =  message = bposApiServices.messages.stream().filter(data->data.equals(exception.getMessageCode())).findFirst().get().getMsgValue();
        if (Objects.equals(exception.getMessageCode(), "MSG009") || Objects.equals(exception.getMessageCode(), "MSG056"))
            httpStatus = HttpStatus.OK;
        else if (Objects.equals(exception.getMessageCode(), "MSG001") || Objects.equals(exception.getMessageCode(), "MSG002") || Objects.equals(exception.getMessageCode(), "MSG003") || Objects.equals(exception.getMessageCode(), "MSG006"))
            httpStatus = HttpStatus.UNAUTHORIZED;
        else if (Objects.equals(exception.getMessageCode(), "MSG004") || Objects.equals(exception.getMessageCode(), "MSG005"))
            httpStatus = HttpStatus.FORBIDDEN;
        else if (Objects.equals(exception.getMessageCode(), "MSG052"))
            httpStatus = HttpStatus.CONFLICT;
        return ResponseEntity.status(httpStatus).body(APIRequest.<Void>builder()
                .messageCode(exception.getMessageCode())
                .message(message)
                .build());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIRequest<Void>> handleAllUncaughtException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIRequest.<Void>builder()
                .messageCode("MSG011")
                .message(bposApiServices.messages.stream().filter(data->data.equals("MSG011")).findFirst().get().getMsgValue())
                .exceptionMessage(exception.getMessage())
                .build());
    }
}