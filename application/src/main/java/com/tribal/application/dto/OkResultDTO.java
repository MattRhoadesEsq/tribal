package com.tribal.application.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class OkResultDTO extends ResultDTO {


    public OkResultDTO() {
        super(OkResultDTO.class, "OK");
    }

    public OkResultDTO(BaseDTO result) {
        super(OkResultDTO.class, "OK");

        setResult(result);
    }

}
