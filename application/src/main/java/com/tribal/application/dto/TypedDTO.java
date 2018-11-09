package com.tribal.application.dto;

public class TypedDTO extends BaseDTO {

    private final Class<?> type;

    protected TypedDTO(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return this.type;
    }

}
