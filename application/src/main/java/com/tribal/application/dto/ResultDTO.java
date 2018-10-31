package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDTO extends TypedDTO {
    private String status;

    public ResultDTO(
            Class<?> type,
            @JsonProperty("status") String status) {
        super(type);

        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
