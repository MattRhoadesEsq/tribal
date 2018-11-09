package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResultDTO extends ResultDTO {
    private String message;

    public ErrorResultDTO() {
        super(ErrorResultDTO.class, "ERROR");
    }

    public ErrorResultDTO(String message) {
        super(ErrorResultDTO.class, "ERROR");

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
