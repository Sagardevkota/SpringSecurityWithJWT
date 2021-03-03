package com.example.sagar.SpringSecurityWithJWT.exception;


import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //extending response entity exception handler for validating arguments
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BearerTokenNotFoundException.class)
    public ResponseEntity<Object> handleBearerTokenException(RuntimeException exception) {
        String bodyOfResponse = exception.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = InvalidException.class)
    public ResponseEntity<Object> handleResourceNotFound(RuntimeException exception) {
        String bodyOfResponse = exception.getMessage();
        return ResponseEntity.badRequest().body(bodyOfResponse);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<JsonResponse> handleUnauthorized(RuntimeException exception) {
        return ResponseEntity.badRequest()
                .body(new JsonResponse(HttpStatus.UNAUTHORIZED.toString(),
                        exception.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<JsonResponse> handleSqlConstraint(RuntimeException exception) {
        return ResponseEntity.badRequest()
                .body(new JsonResponse(HttpStatus.BAD_REQUEST.toString(),
                        exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<JsonResponse> handleAllExceptions(Exception exception, WebRequest request) {
        return ResponseEntity.badRequest()
                .body(new JsonResponse(HttpStatus.BAD_REQUEST.toString(),
                        exception.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put("message",status.getReasonPhrase());
            errors.put(fieldName, message);
        });
        return ResponseEntity.badRequest().body(errors);
    }


}
