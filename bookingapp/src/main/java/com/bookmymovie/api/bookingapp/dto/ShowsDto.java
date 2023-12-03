package com.bookmymovie.api.bookingapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowsDto {


    private Long showId;

    private LocalTime startTime;

    private LocalTime endTime;


    private Set<TicketDto> tickets;
}
