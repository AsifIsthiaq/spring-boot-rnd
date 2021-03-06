package com.springdemo.springbootrnd.converters;

import com.springdemo.springbootrnd.dto.UserDto;
import com.springdemo.springbootrnd.models.User;
import com.springdemo.springbootrnd.util.Utility;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    public UserDto entityToDto(User user) {
        UserDto map = Utility.getModelMapper().map(user, UserDto.class);
        return map;
    }

    public List<UserDto> entityToDto(List<User> users) {
        return users.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }

    public User dtoToEntity(UserDto dto) {
        User map = Utility.getModelMapper().map(dto, User.class);
        return map;
    }

    public List<User> dtoToEntity(List<UserDto> dto) {
        return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
}
