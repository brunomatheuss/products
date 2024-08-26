package com.bruno.products.exceptions.handler;

import com.bruno.products.exceptions.ExceptionResponse;
import com.bruno.products.exceptions.GenericErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ResponseBody
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericError(GenericErrorException ex) {
        var statusCode = ex.getStatusCode().value();
        var body = new ExceptionResponse(ex.getMessage());
        return ResponseEntity.status(statusCode).body(body);
    }

}
