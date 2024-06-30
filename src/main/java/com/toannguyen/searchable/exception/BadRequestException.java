package com.toannguyen.searchable.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
    List<ErrorElement> errorList;

    public BadRequestException(String message, List<ErrorElement> errorList) {
        super(message);
        this.errorList = errorList;
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ErrorElement> errorList) {
        super("Bad client request");
        this.errorList = errorList;
    }
}
