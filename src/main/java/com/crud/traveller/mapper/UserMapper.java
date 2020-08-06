package com.crud.traveller.mapper;

import com.crud.traveller.domain.UserDto;
import com.crud.traveller.entity.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto){
        return new User (
            userDto.getUserId (),
            userDto.getUserName (),
            userDto.getUserKey (),
            userDto.getExcursion (),
            userDto.getCurrencyExchange ());
           // userDto.getWeatherForecast ());
    }
    public UserDto mapToUserDto(final User user){
        return new UserDto (
            user.getUserId (),
            user.getUserName (),
            user.getUserKey (),
            user.getExcursion (),
            user.getCurrencyExchange ());
           // user.getWeatherForecast ());
    }
    public List<UserDto> mapToUserDtoList (final List<User> userList){
        return userList.stream ()
                .map (u-> new UserDto (u.getUserId (), u.getUserName (), u.getUserKey (),
                        u.getExcursion (), u.getCurrencyExchange ()))
                .collect(Collectors.toList ());
    }
}
