package com.ridoy.villa.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomType {
    SINGLE,
    DOUBLE,
    SUITE;

    @JsonCreator
    public static RoomType fromString(String value) {
        return RoomType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name();
    }
}


