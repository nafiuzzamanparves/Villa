package com.ridoy.villa.util;

public class ResponseUtil {

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> failed(String message, T data) {
        return new ApiResponse<>("failed", message, data);
    }
}
