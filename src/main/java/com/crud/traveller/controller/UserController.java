package com.crud.traveller.controller;

import com.crud.traveller.domain.UserDto;
import com.crud.traveller.exception.UserNotFoundException;
import com.crud.traveller.mapper.UserMapper;
import com.crud.traveller.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserDbService userDbService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userDbService.findAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userDbService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        userDbService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping (method = RequestMethod.PUT, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userDbService.saveUser(userMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@RequestParam Long userId) throws UserNotFoundException {
        if (userDbService.isExist(userId)) {
            userDbService.deleteUser(userId);
        }else{
            throw new UserNotFoundException();
        }
    }
}