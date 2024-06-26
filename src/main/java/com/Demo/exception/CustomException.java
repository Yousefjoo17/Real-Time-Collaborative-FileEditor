package com.Demo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CustomException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
}
