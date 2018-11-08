package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResultDTO extends ResultDTO {
    private String message;

    public ErrorResultDTO(
        @JsonProperty("message") String message) {

        super("ERROR");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
