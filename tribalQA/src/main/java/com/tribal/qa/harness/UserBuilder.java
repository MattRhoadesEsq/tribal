package com.tribal.qa.harness;

import com.tribal.application.dto.OkResultDTO;
import com.tribal.application.dto.UserDTO;
import com.tribal.qa.webservices.RestClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserBuilder {
    private static Logger logger = LogManager.getLogger(UserBuilder.class);

    private String email = Randomness.randomEmail();

    public UserBuilder() {

    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Just define the user, don't create on the server.
     * @return
     */
    public UserDTO define() {
        UserDTO dto = new UserDTO();
        dto.setEmail(this.email);
        return dto;
    }

    /**
     * Create the user on the server
     * @return
     */
    public UserDTO create() {
        RestClient client = new RestClient();
        OkResultDTO result = (OkResultDTO) client.doPOST(define());
        return (UserDTO) result.getResult();
    }

}
