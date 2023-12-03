package com.bookmymovie.api.bookingapp.controller;

import com.bookmymovie.api.bookingapp.dto.ResponseDto;
import com.bookmymovie.api.bookingapp.dto.UserDto;
import com.bookmymovie.api.bookingapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookmymovie.api.bookingapp.constants.BookingAppConstants.messageCreated;

@RestController
@RequestMapping(path = "/bookmymovie/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserControllerApi {

    private final UserService userService;


    public UserControllerApi(UserService theatreService) {
        this.userService = theatreService;
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) {

        Long id = userService.creatUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.name(), messageCreated("User", id.toString())));


    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userDtos);

    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);

    }


}
