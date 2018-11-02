package com.tribal.application;

import com.tribal.application.dto.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);


    @RequestMapping(method = RequestMethod.GET)
    public ResultDTO getUser(@RequestParam(value="email", defaultValue="foo@bar.com") String email) {
        logger.trace("GET");

        UserDTO user = new UserDTO(email);
        return new OkResultDTO(user);
    }

}