package com.restapi.manufacture.exception;

import com.restapi.manufacture.model.FailureResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<FailureResponse> handleDataNotFoundException(DataNotFoundException ex) {
        log.error(ex.getMessage());

        return ResponseEntity.badRequest()
                .body(FailureResponse.builder()
                        .timeStamp(new Date().getTime())
                        .message("The parameter you sent is invalid / not match")
                        .errors(new String[]{ex.getMessage()})
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailureResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());

        String[] errors = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage())
                .toArray(String[]::new);

        return ResponseEntity.badRequest()
                .body(FailureResponse.builder()
                        .timeStamp(new Date().getTime())
                        .message("Arguments not valid / match")
                        .errors(errors)
                        .build()
                );
    }
}
