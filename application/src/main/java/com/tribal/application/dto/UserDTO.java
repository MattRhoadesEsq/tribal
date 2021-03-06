package com.tribal.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO extends TypedDTO {
    private String id;
    private String email;

    public UserDTO() {
        super(UserDTO.class);
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }



}