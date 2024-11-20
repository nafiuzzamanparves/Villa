package com.ridoy.villa.util;

public class ResponseUtil {

    // Utility method for success responses
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, "SUCCESS", data);
    }
}
