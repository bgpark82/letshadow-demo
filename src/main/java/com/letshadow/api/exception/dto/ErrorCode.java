package com.letshadow.api.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_NAME("2000","이름은 필수값입니다", 400),
    NOT_FOUND_PERSON("2001","사람을 찾을 수 없습니다",404),
    METHOD_ARGUMENT_NOT_VALID("2002","아규먼트 오류",500),
    RENAME_NOT_PERMITTED("2003","이름 변경이 허용되지 않습니다",400);


    private final String code;
    private final String message;
    private final int status;
}
