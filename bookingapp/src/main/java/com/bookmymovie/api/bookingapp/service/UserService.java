package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.UserDto;

import java.util.List;

public interface UserService {

    /**
     * @param userDto
     */
    Long creatUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

}
