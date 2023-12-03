package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.dto.UserDto;
import com.bookmymovie.api.bookingapp.entity.User;
import com.bookmymovie.api.bookingapp.exception.ResourceNotFoundExcption;
import com.bookmymovie.api.bookingapp.exception.UserAlreadyExistsException;
import com.bookmymovie.api.bookingapp.mapper.UserMapper;
import com.bookmymovie.api.bookingapp.repository.UserRepository;
import com.bookmymovie.api.bookingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Long creatUser(UserDto userDto) {
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(
                    "User already exists with the email" + existingUser.get().getEmail());
        }
        User user = UserMapper.mapToUSer(userDto, new User());
        return userRepository.save(user).getUserId();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(e -> UserMapper.mapToUSerDto(e, new UserDto())).toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User existingUser = userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundExcption("User", "id", id.toString()));

        return UserMapper.mapToUSerDto(existingUser, new UserDto());
    }
}
