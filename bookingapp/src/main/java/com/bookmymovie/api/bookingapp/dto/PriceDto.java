package com.bookmymovie.api.bookingapp.dto;

import com.bookmymovie.api.bookingapp.constants.SeatType;
import lombok.Data;

@Data
public class PriceDto {

    
    private Double amount;

    private SeatType seatType;

}
