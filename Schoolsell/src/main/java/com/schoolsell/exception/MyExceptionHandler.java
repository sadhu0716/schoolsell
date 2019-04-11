package com.schoolsell.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    Logger logger=LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = MySqlException.class)
    public void SqlExceptionHandler(MySqlException e){
        logger.info(e.getMessage());
        System.out.println(e.getMessage());
    }

    @ExceptionHandler(value = MyOtherException.class)
    public void OtherExceptionHandler(MyOtherException e){
        logger.info(e.getMessage());
        System.out.println(e.getMessage());
    }

}
