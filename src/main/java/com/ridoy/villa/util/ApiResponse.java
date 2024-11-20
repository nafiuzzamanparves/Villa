package com.ridoy.villa.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private String status;
    private T data; // Generic type for response data
    private long timestamp;

    public ApiResponse(String message, String status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
}
