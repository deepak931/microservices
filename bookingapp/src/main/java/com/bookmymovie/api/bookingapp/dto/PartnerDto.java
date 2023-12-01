package com.bookmymovie.api.bookingapp.dto;


import lombok.Data;

@Data
public class PartnerDto {

  private Long clientId;

  private String name;

  private String email;

  private String mobileNumber;

  private String address;

  private String city;
}
