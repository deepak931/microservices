package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {



  private Long userId;

  private String name;

  private String email;

  private String phoneNo;

  private Set<BookingDto> bookings;
}
