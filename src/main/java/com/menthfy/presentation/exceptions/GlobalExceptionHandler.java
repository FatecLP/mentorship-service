package com.menthfy.presentation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/** EN: Converts business runtime exceptions into the API's error response format.
 * PT-BR: Converte exceções de negócio no formato de erro da API. */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    /**
     * Handles a business exception as a bad request.
     *
     * @param ex exception raised by an application use case
     * @return HTTP 400 response containing the exception message
     */
    public ResponseEntity<?> handleRuntime(RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "message", ex.getMessage()
                ));
    }
}
