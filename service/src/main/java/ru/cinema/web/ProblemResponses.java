package ru.cinema.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

/** Shared representation for errors raised by MVC and by the security filter chain. */
@Component
public final class ProblemResponses {
    private final ObjectMapper objectMapper;

    public ProblemResponses(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ProblemDetail create(HttpStatus status, String path) {
        String detail = switch (status) {
            case BAD_REQUEST -> "Request parameters or body are invalid.";
            case UNAUTHORIZED -> "A valid bearer token is required.";
            case FORBIDDEN -> "You are not allowed to access this resource.";
            case NOT_FOUND -> "The requested resource was not found.";
            case METHOD_NOT_ALLOWED -> "This HTTP method is not supported for this resource.";
            case NOT_ACCEPTABLE -> "The requested response media type is not supported.";
            case UNSUPPORTED_MEDIA_TYPE -> "The request media type is not supported.";
            case CONFLICT -> "The request conflicts with the current resource state.";
            case NOT_IMPLEMENTED -> "Business logic has not been implemented in this API skeleton.";
            default -> status.is5xxServerError() ? "An unexpected server error occurred." : "The request could not be processed.";
        };
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setType(URI.create("about:blank"));
        problem.setInstance(URI.create(path));
        problem.setProperty("code", status == HttpStatus.BAD_REQUEST ? "INVALID_REQUEST" : status.name());
        return problem;
    }

    public void write(HttpStatus status, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), create(status, request.getRequestURI()));
    }
}
