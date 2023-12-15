package com.bookmymovie.api.bookingapp.controller;

import com.bookmymovie.api.bookingapp.dto.*;
import com.bookmymovie.api.bookingapp.exception.CreateMovieException;
import com.bookmymovie.api.bookingapp.exception.ShowsTimeOverlapException;
import com.bookmymovie.api.bookingapp.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bookmymovie.api.bookingapp.constants.BookingAppConstants.messageCreated;

@RestController
@RequestMapping(path = "/bookmymovie/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieControllerApi {


    private final MovieService movieService;

    public MovieControllerApi(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movie")
    public ResponseEntity<ResponseDto> createMovie(@RequestBody MovieRequestDto movieDto) {
        if (!validateShows(movieDto.getShows())) {
            throw new ShowsTimeOverlapException("Start and end time of two shows overlaps");
        }
        if (!validateMovieShowsAndPrice(movieDto)) {
            throw new CreateMovieException("Running status of movie is TRUE. Please provide shows and price detail " +
                    "also");
        }
        Long id = movieService.creatMovie(movieDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.name(), messageCreated("Movie", id.toString())));
    }


    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> list = movieService.getAllMovies();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long id) {
        MovieDto movieDto = movieService.getMovieById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movieDto);
    }

    @GetMapping("/movies/search")
    public ResponseEntity<List<MovieShowInfoDto>> getMovie(@RequestParam String name, String city,
                                                           LocalDate date) {
        List<MovieShowInfoDto> movieDtos = movieService.getMoviesByNameAndCity(name, city, date);
        return ResponseEntity.status(HttpStatus.OK).body(movieDtos);
    }

    private boolean validateMovieShowsAndPrice(MovieRequestDto movieDto) {
        return !movieDto.getIsRunning() || ((movieDto.getPrice() != null && !movieDto.getPrice().isEmpty()) && (movieDto.getShows() != null && !movieDto.getShows().isEmpty()));
    }

    private boolean validateShows(Set<ShowsDto> shows) {

        List<ShowsDto> showsDtoList =
                shows.stream().sorted((e1, e2) -> e1.getStartTime().compareTo(e2.getEndTime()))
                        .collect(Collectors.toCollection(ArrayList::new));

        Iterator<ShowsDto> iterator = showsDtoList.iterator();
        ShowsDto prev = iterator.next();
        while (iterator.hasNext()) {
            ShowsDto curr = iterator.next();
            if (!prev.getEndTime().isBefore(curr.getStartTime()))
                return false;
            prev = curr;
        }

        return true;

    }


}
