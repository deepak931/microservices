package com.bookmymovie.api.bookingapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDto {


    private Long movieId;

    @NotEmpty
    private String title;

    @NotEmpty
    private LocalDateTime releaseDate;

    private String director;

    private String genre;

    private Set<ShowsDto> shows;

    @NotEmpty
    private Long theatreId;

    private Set<TheatreDto> theatres;


}
