package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TypedDTO extends BaseDTO {

    private final String type;

    protected TypedDTO(
        @JsonProperty("type") Class<?> type) {

        this.type = type.getCanonicalName();
    }

    public String getType() {
        return type;
    }
}
