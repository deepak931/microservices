package com.bookmymovie.api.bookingapp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TheatreDto {
  private Long theatreId;

  private String theatreName;

  private String theatreEmail;

  private String contactNumber;

  private String address;

  private String city;

  private Long partnerId;

  private PartnerDto partnerDto;

  private LocalTime time = LocalTime.now();

  private LocalDateTime dateTime = LocalDateTime.now();

  private Set<SeatDto> seats;
}
