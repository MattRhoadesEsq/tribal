package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OkResultDTO extends ResultDTO {

    @JsonProperty("result")
    public BaseDTO result;

    public OkResultDTO(
        @JsonProperty("result") BaseDTO result) {
        super("OK");

        this.result = result;
    }

    public BaseDTO getResult() {
        return result;
    }

    public void setResult(BaseDTO result) {
        this.result = result;
    }


}
