package com.crud.traveller.mapper;

import com.crud.traveller.domain.UserDto;
import com.crud.traveller.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestSuite {
    @Autowired
    UserMapper userMapper;

    @Test
    public void mapToUser(){
        //Given
        UserDto userDto1 = new UserDto (1L, "Darek", "123", null, null, null);
        //When
        User mappingResult= userMapper.mapToUser(userDto1);
        //Then
        Assert.assertEquals(1L, mappingResult.getUserId ().longValue ());
        Assert.assertEquals("Darek", mappingResult.getUserName ());
        Assert.assertEquals ("123", mappingResult.getUserKey ());
    }

    @Test
    public void mapToUserDto(){
        //Given
        User user1 = new User (1L, "Darek", "123", null, null, null);
        //When
        UserDto mappingResult=userMapper.mapToUserDto (user1);
        //Then
        Assert.assertEquals(1L, mappingResult.getUserId ().longValue ());
        Assert.assertEquals("Darek", mappingResult.getUserName ());
        Assert.assertEquals ("123", mappingResult.getUserKey ());
    }

    @Test
    public void mapToUserDtoList(){
        //Given
        User user1 = new User (1L, "Darek", "123", null, null, null);
        List<User> usersList = new ArrayList<> ();
        usersList.add (user1);
        //When
        List<UserDto> mappingResult=userMapper.mapToUserDtoList (usersList);
        //Then
        Assert.assertEquals(1L, mappingResult.get(0).getUserId ().longValue ());
        Assert.assertEquals("Darek", mappingResult.get(0).getUserName ());
        Assert.assertEquals ("123", mappingResult.get(0).getUserKey ());
    }
}