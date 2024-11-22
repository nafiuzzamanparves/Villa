package com.ridoy.villa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BaseDTO {
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String createdBy;
    private final String updatedBy;
    private final boolean isActive;

    // Constructor
    public BaseDTO(LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy, boolean isActive) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.isActive = isActive;
    }
}
