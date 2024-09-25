package com.phaete.backend;

import com.phaete.backend.model.ErrorMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorMessage handleAllUncaughtExceptions(Exception e) {
        return new ErrorMessage(e.getClass().getName(), "Could not complete request.\n" + e.getMessage());
    }
}
