package hr.fer.progi.backend.dto;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(int status, String message, T data) {

    public static <T> ApiResponse<T> ok(T data) {
        var status = HttpStatus.OK;
        return new ApiResponse<T>(status.value(), status.getReasonPhrase(), data);
    }
}
