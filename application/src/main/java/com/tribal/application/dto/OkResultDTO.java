package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OkResultDTO extends ResultDTO {


    public OkResultDTO() {
        super(OkResultDTO.class, "OK");
    }

    public OkResultDTO(Class<?> type) {
        super(type, "OK");
    }

    public OkResultDTO(Object result) {
        super(OkResultDTO.class, "OK");

        setResult(result);
    }


}
