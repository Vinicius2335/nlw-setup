package com.viniciusvieira.server.api.exceptionhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {

        List<Problem.Field> fields = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError -> fields.add(new Problem.Field(
                fieldError.getField(),
                fieldError.getDefaultMessage()
        )));

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setTimestamp(OffsetDateTime.now());
        problem.setTitle("Um ou mais campos estão inválido. Faça o preenchimento correto e tente novamente.");
        problem.setFields(fields);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }
}
