package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO extends TypedDTO {
    private String email;


    public UserDTO(
            @JsonProperty("email") String email) {
        super(UserDTO.class);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }



}