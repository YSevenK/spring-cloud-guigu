package com.ysevenk.order.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler(Throwable.class)
    // public String error(Throwable e) {
    //     return "";
    // }
}
