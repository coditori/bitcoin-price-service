package com.searchmetrics.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebClientResponseException.BadRequest.class)
    public ResponseEntity<Object> handleNotFoundException(
            WebClientResponseException.BadRequest ex, WebRequest request) {

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleBadRequestException(
            Exception ex, WebRequest request) {

        return ResponseEntity.badRequest().build();
    }
}