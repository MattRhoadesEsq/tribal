package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OkResultDTO extends ResultDTO {
    private Object result;

    public OkResultDTO(
        @JsonProperty("result") Object result) {

        super(OkResultDTO.class, "OK");

        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
