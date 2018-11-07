package com.tribal.application;

import com.tribal.application.dto.UserDTO;
import org.modelmapper.ModelMapper;

public class User {

    private int id;
    private String email;

    private static int index = 1;

    public User(String email) {
        id = index++;
        this.email = email;
    }

    public User(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return  this.id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof User)) {
            return false;
        }
        User other = (User)o;
        return (this.id == other.id);
    }

    protected static ModelMapper theMapper = null;
    public static ModelMapper getMapper() {
        if (theMapper != null) {
            return theMapper;
        }
        theMapper = new ModelMapper();
        return theMapper;
    }

    public static UserDTO convertToDto(User u) {
        UserDTO dto = getMapper().map(u, UserDTO.class);
        return dto;
    }

    public static User convertToEntity(UserDTO dto) {
        User u = getMapper().map(dto, User.class);
        return u;
    }

}
