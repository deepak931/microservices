package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.UserDto;

import java.util.List;

public interface UserService {

  /**
   * @param userDto
   */
  void creatUser(UserDto userDto);

  List<UserDto> getAllUsers();

}
