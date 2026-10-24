package hr.fer.progi.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

record ErrorResponse(
        int status,
        String error,
        String message,
        LocalDateTime timestamp,
        String path
) {
}

@ControllerAdvice
public class GlobalExceptionHandler {

    // SVE JE GENERIC
    // todo: dodat sve exceptione i opise

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var response = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(response);
    }
}
