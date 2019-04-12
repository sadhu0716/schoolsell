package com.schoolsell.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class MySqlException extends RuntimeException {
    public MySqlException(String message) {
        super(message);
    }
}
