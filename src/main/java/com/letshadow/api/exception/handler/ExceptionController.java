package com.letshadow.api.exception.handler;

import com.letshadow.api.exception.PersonNotFoundException;
import com.letshadow.api.exception.RenameNotPermittedException;
import com.letshadow.api.exception.dto.ErrorCode;
import com.letshadow.api.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlePersonNotFoundException(PersonNotFoundException ex){
        return ErrorResponse.of(ErrorCode.NOT_FOUND_PERSON);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ErrorResponse.of("400",ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(RenameNotPermittedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRenameNotPermittedException(RenameNotPermittedException ex){
        return ErrorResponse.of(ErrorCode.RENAME_NOT_PERMITTED);
    }

}
