package com.bookmymovie.api.bookingapp.dto;

import com.bookmymovie.api.bookingapp.constants.SeatType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatDto {


  private Long seatId;

  private String seatNo;

  private Integer type;

  private SeatType seatType;


}
