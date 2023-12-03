package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.TheatreDto;
import com.bookmymovie.api.bookingapp.dto.TheatreRequestDto;

import java.util.List;

public interface TheatreService {

    /**
     * @param theatreDto
     */
    Long creatTheatre(TheatreRequestDto theatreDto);

    List<TheatreDto> getAllTheatre(boolean isSeats);

    TheatreDto getTheatreById(Long id);

}
