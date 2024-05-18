package com.sea.challenge.register.exceptions.exceptionhandler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sea.challenge.register.exceptions.CpfAlreadyExistsException;
import com.sea.challenge.register.exceptions.InvalidPhoneNumberException;
import com.sea.challenge.register.exceptions.security.UserNameAlreadyExistsException;
import com.sea.challenge.register.exceptions.viacep.InvalidCepException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionFilter> handleValidationErrors(MethodArgumentNotValidException exception) {
        ExceptionFilter exceptionFilter = ExceptionFilter.builder()
                .title("field not valid error")
                .timestamp(LocalDateTime.now())
                .devMsg(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(exception
                        .getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(error -> {
                            String errorMessage = error.getDefaultMessage();
                            String field = ((FieldError) error).getField();
                            String value = ((FieldError) error).getRejectedValue().toString();
                            return getErrorMsg(field, value, errorMessage);
                        })
                        .collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(exceptionFilter, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionFilter> handleValidationException(HttpMessageNotReadableException exception) {
        ExceptionFilter exceptionFilter = ExceptionFilter.builder()
                .title("invalid enum error")
                .timestamp(LocalDateTime.now())
                .devMsg(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();

        if (exception.getCause() instanceof InvalidFormatException ifx) {
            if (ifx.getTargetType() != null && ifx.getTargetType().isEnum()) {
                exceptionFilter.setDetails(List.of(getErrorMsg(
                                ifx.getPath().get(ifx.getPath().size()-1).getFieldName(),
                                ifx.getValue(),
                                "available values: " + Arrays.toString(ifx.getTargetType().getEnumConstants()))));
            }
        }
        return new ResponseEntity<>(exceptionFilter, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidCepException.class)
    public ResponseEntity<ExceptionFilter> handleValidationException(InvalidCepException exception) {
        ExceptionFilter exceptionFilter = ExceptionFilter.builder()
                .title("invalid cep error")
                .timestamp(LocalDateTime.now())
                .devMsg(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(List.of(getErrorMsg("cep", exception.getValue(), exception.getMessage())))
                .build();
        return new ResponseEntity<>(exceptionFilter, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionFilter> handlerUsernameNotFoundException(UsernameNotFoundException exception) {
        ExceptionFilter exceptionFilter = ExceptionFilter.builder()
                .title("username not found")
                .timestamp(LocalDateTime.now())
                .devMsg(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .details(List.of(getErrorMsg("username", "", "")))
                .build();
        return new ResponseEntity<>(exceptionFilter, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<ExceptionFilter> handleCpfAlreadyExistsException(CpfAlreadyExistsException exception) {
        ExceptionFilter exceptionFilter = ExceptionFilter.builder()
                .title("data integrity violation")
                .timestamp(LocalDateTime.now())
                .devMsg(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(List.of(getErrorMsg("cpf", exception.getCpf(), exception.getMessage())))
                .build();

        return new ResponseEntity<>(exceptionFilter, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ExceptionFilter> handleInvalidPhoneNumberException(InvalidPhoneNumberException exception) {
        ExceptionFilter exceptionFilter = ExceptionFilter.builder()
                .title("data integrity violation")
                .timestamp(LocalDateTime.now())
                .devMsg(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(List.of(getErrorMsg("phones", exception.getPhone(), exception.getMessage())))
                .build();
        return new ResponseEntity<>(exceptionFilter, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<ExceptionFilter> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException exception) {
        ExceptionFilter exceptionFilter = ExceptionFilter.builder()
                .title("data integrity violation")
                .timestamp(LocalDateTime.now())
                .devMsg(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(List.of(getErrorMsg("username", exception.getUsername(), exception.getMessage())))
                .build();
        return new ResponseEntity<>(exceptionFilter, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ExceptionFilter> handleJWTVerificationException(JWTVerificationException exception) {
        ExceptionFilter exceptionFilter = ExceptionFilter.builder()
                .title("jwt validate error")
                .timestamp(LocalDateTime.now())
                .devMsg(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .details(List.of(getErrorMsg("jwt token", "", exception.getMessage())))

                .build();
    }

    private String getErrorMsg(String field, Object value, String message) {
        String ERROR_MSG = "field: [ %s ], value:[ %s ] => error: [ %s ]";
        return String.format(ERROR_MSG, field, value.toString(), message);
    }
}
