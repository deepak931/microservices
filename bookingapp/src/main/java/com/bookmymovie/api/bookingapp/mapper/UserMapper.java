package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.UserDto;
import com.bookmymovie.api.bookingapp.entity.User;

public class UserMapper {

  public static User mapToUSer(UserDto userDto, User user) {
    return user;
  }

  public static UserDto mapToUSerDto(User user, UserDto userDto) {
    return userDto;
  }
}
