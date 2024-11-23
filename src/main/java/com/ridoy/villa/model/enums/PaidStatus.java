package com.ridoy.villa.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaidStatus {
    PAID, PARTIAL, UNPAID;

    @JsonCreator
    public static PaidStatus fromString(String value) {
        return PaidStatus.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}
