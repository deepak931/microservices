package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.UserDto;
import com.bookmymovie.api.bookingapp.entity.User;

public class UserMapper {

    public static User mapToUSer(UserDto userDto, User user) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNo(userDto.getPhoneNo());
        return user;
    }

    public static UserDto mapToUSerDto(User user, UserDto userDto) {
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNo(user.getPhoneNo());
        userDto.setUserId(user.getUserId());
        return userDto;
    }
}
