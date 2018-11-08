package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultDTO extends BaseDTO {
    private String status;

    public ResultDTO() {
    }

    public ResultDTO(
            @JsonProperty("status") String status) {

        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
