package com.ridoy.villa.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CollectionType {
    MONTHLY,
    YEARLY;

    @JsonCreator
    public static CollectionType fromString(String value) {
        return CollectionType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}
