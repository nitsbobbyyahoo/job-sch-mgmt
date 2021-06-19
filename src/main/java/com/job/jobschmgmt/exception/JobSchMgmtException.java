package com.job.jobschmgmt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class JobSchMgmtException {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleRequestException(MethodArgumentNotValidException validationException) {

    List<FieldError> fieldErrorList = validationException.getBindingResult().getFieldErrors();
    String errorMessages = fieldErrorList.stream()
        .map(fieldError -> "Input Field: " + fieldError.getField() + " - " + fieldError.getDefaultMessage()).
            sorted().collect(Collectors.joining(","));
    log.info("Error message : {}", errorMessages);
    return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
  }
}
