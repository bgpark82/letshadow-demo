package com.letshadow.api.exception.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String code;
    private String message;

    public static ErrorResponse of(ErrorCode e){
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

    public static ErrorResponse of(String code, String message){
        return new ErrorResponse(code, message);
    }
}
