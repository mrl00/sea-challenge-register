package com.sea.challenge.register.exceptions.exceptionhandler;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    String errorMessage = error.getDefaultMessage();
                    String field = ((FieldError) error).getField();
                    String value = ((FieldError) error).getRejectedValue().toString();

                    return String.format("field: %s (%s) => error: %s", field, value, errorMessage);
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationException(HttpMessageNotReadableException exception) {
        String errorDetails = "";

        if (exception.getCause() instanceof InvalidFormatException) {
            InvalidFormatException ifx = (InvalidFormatException) exception.getCause();
            if (ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
                errorDetails = String.format("field: %s (%s) => error: the values must be %s",
                        ifx.getPath().get(ifx.getPath().size()-1).getFieldName(), ifx.getValue(), Arrays.toString(ifx.getTargetType().getEnumConstants()));
            }
        }
        return new ResponseEntity<>(getErrorsMap(List.of(errorDetails)), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
