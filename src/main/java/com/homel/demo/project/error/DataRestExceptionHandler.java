package com.homel.demo.project.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DataRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DataErrorResponse> handleException(Exception e) {
        DataErrorResponse dataErrorResponse = new DataErrorResponse();

        dataErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dataErrorResponse.setMessage(e.getMessage());
        dataErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(dataErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
