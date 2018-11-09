package com.tribal.application;

import com.tribal.application.dto.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResultDTO createUser(@RequestBody UserDTO dto)  {
        logger.trace("POST");

        User user = UserRepository.instance().getByEmail(dto.getEmail());
        if (user != null) {
            return updateUser(dto);
        }

        UserDTO userDTO = User.convertToDto(UserRepository.instance().add(dto.getEmail()));
        return new OkResultDTO(userDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResultDTO readUser(@RequestParam(value="email", required = false) String email) {
        logger.trace("GET");

        if (email != null) {
            User user = UserRepository.instance().getByEmail(email);
            return new OkResultDTO(User.convertToDto(user));
        }

        List<BaseDTO> dtos = new ArrayList<>();
        for (User u : UserRepository.instance().getAll()) {
            dtos.add(User.convertToDto(u));
        }
        return new ListResultDTO(dtos);
    }

    public @ResponseBody ResultDTO updateUser(UserDTO dto) {
        return new OkResultDTO(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResultDTO deleteUser(@PathVariable("id") String id) {

        User user = UserRepository.instance().getById(id);
        if (user == null) {
            return new ErrorResultDTO("No such ID: "+ id);
        }

        UserRepository.instance().delete(id);
        UserDTO dto = User.convertToDto(user);
        return new OkResultDTO(dto);
    }
}