package ru.cinema.web;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.cinema.dto.FieldViolationDto;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);
    private final ProblemResponses problems;

    public ApiExceptionHandler(ProblemResponses problems) {
        this.problems = problems;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problem = problems.create(HttpStatus.valueOf(status.value()), path(request));
        if (exception instanceof MethodArgumentNotValidException validation) {
            List<FieldViolationDto> errors = validation.getBindingResult().getFieldErrors().stream()
                    .map(error -> new FieldViolationDto(error.getField(), error.getDefaultMessage()))
                    .toList();
            problem.setProperty("errors", errors);
        }
        // Never return exception messages, rejected values, SQL or stack traces.
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.putAll(headers);
        responseHeaders.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return new ResponseEntity<>(problem, responseHeaders, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {
        ProblemDetail problem = problems.create(HttpStatus.BAD_REQUEST, path(request));
        problem.setProperty("errors", exception.getConstraintViolations().stream()
                .map(violation -> {
                    String property = violation.getPropertyPath().toString();
                    return new FieldViolationDto(property.substring(property.lastIndexOf('.') + 1), violation.getMessage());
                }).toList());
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_PROBLEM_JSON).body(problem);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleUnexpected(Exception exception, WebRequest request) {
        LOG.error("Unhandled request failure", exception);
        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<Object> handleAccessDenied(AccessDeniedException exception, WebRequest request) {
        return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    private String path(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}
