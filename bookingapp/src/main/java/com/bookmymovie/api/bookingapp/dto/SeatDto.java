package com.bookmymovie.api.bookingapp.dto;

import com.bookmymovie.api.bookingapp.constants.SeatType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeatDto {


    private Long seatId;

    @NotEmpty
    private String seatNo;

    @Max(value = 3)
    @Min(value = 1)
    private Integer type;

    private SeatType seatType;


}
