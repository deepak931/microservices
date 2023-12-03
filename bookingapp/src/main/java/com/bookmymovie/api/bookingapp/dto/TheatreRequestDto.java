package com.bookmymovie.api.bookingapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TheatreRequestDto {

    @NotEmpty
    private String theatreName;
    @NotEmpty
    @Email
    private String theatreEmail;
    @NotEmpty
    private String contactNumber;

    private String address;
    @NotEmpty
    private Long cityId;

    @NotEmpty
    private Long partnerId;

    private Set<SeatDto> seats;
}
