package com.crud.traveller.controller;

import com.crud.traveller.domain.UserDto;
import com.crud.traveller.entity.*;
import com.crud.traveller.mapper.UserMapper;
import com.crud.traveller.service.UserDbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDbService userDbService;
    @MockBean
    private UserMapper userMapper;

    @Test
    public void getUsers() throws Exception{
        //Given
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        User user = new User(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        List<User> userList = new ArrayList<> ();
        userList.add(user);

        UserDto userDto = new UserDto(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        List<UserDto> userDtoList = new ArrayList<> ();
        userDtoList.add(userDto);

        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);
        when(userDbService.findAllUsers ()).thenReturn(userList);
        //When&Then
        mockMvc.perform(get("/v1/users").contentType( MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].userName", is("Darek")))
                .andExpect(jsonPath("$[0].userKey", is("123")))
                .andExpect(jsonPath("$[0].excursion", is(excursionsItaly)));
        }
    @Test
    public void getUser() throws Exception{
        //Given
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        User user = new User(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        UserDto userDto = new UserDto(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        when(userDbService.getUser(user.getUserId())).thenReturn(Optional.of(user));
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        //When&Then
        mockMvc.perform(get("/v1/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is (1)))
                .andExpect(jsonPath("$.userName", is("Darek")))
                .andExpect(jsonPath("$.userKey", is("123")))
                .andExpect(jsonPath("$.excursion", is(excursionsItaly)));
    }
    @Test
    public void createUser() throws Exception{
        //Given
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        User user = new User(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        UserDto userDto = new UserDto(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userDbService.saveUser (ArgumentMatchers.any(User.class))).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When&Then
        mockMvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        //Given
        List<Excursion> excursionsItaly = new ArrayList<> ();
        List<Excursion> excursionsItaly2 = new ArrayList<> ();
        List<Currency> currencyItaly = new ArrayList<> ();
        List<Weather> weatherItaly = new ArrayList<> ();
        User user = new User(1L, "Darek", "123", excursionsItaly, currencyItaly, weatherItaly);
        UserDto userDto = new UserDto(1L, "Darek", "1234", excursionsItaly2, currencyItaly, weatherItaly);

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userDbService.saveUser (ArgumentMatchers.any(User.class))).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When&Then
        mockMvc.perform(put("/v1/users").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.userName", is("Darek")))
                .andExpect(jsonPath("$.userKey", is("1234")))
                .andExpect(jsonPath("$.excursion", is(excursionsItaly2)));
    }

    @Test
    public void deleteUser() throws Exception{
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/users/1"))
                .andExpect(status().is(200));

        verify(userDbService, times(1)).deleteUser(any());
    }
}