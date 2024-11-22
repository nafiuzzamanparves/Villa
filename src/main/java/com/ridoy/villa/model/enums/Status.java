package com.ridoy.villa.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    AVAILABLE,
    OCCUPIED,
    MAINTENANCE;

    @JsonCreator
    public static Status fromString(String value) {
        return Status.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}