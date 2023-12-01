package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

@Data
public class CityDto {


  private Long cityId;

  private String name;

  private String zipCode;
}
