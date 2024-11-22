package com.ridoy.villa.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {
    CASH,
    CHECK,
    CREDIT_CARD;

    @JsonCreator
    public PaymentType fromString(String value) {
        return PaymentType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}