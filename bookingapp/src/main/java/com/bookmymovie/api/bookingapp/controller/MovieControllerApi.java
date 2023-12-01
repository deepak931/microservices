package com.bookmymovie.api.bookingapp.controller;

import com.bookmymovie.api.bookingapp.constants.BookingAppConstants;
import com.bookmymovie.api.bookingapp.dto.MovieDto;
import com.bookmymovie.api.bookingapp.dto.ResponseDto;
import com.bookmymovie.api.bookingapp.dto.ShowsDto;
import com.bookmymovie.api.bookingapp.exception.ShowsTimeOverlapException;
import com.bookmymovie.api.bookingapp.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieControllerApi {


  private final MovieService movieService;

  public MovieControllerApi(MovieService movieService) {
    this.movieService = movieService;
  }

  @PostMapping("/movie")
  public ResponseEntity<ResponseDto> createMovie(@RequestBody MovieDto movieDto) {
    if (!validateShows(movieDto.getShows())) {
      throw new ShowsTimeOverlapException("Start and end time of two shows overlaps");
    }
    movieService.creatMovie(movieDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(HttpStatus.CREATED.name(), BookingAppConstants.MESSAGE_201));
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
  public ResponseEntity<List<MovieDto>> getMovie(@RequestParam String name, String city) {
    List<MovieDto> movieDtos = movieService.getMoviesByNameAndCity(name, city);
    return ResponseEntity.status(HttpStatus.OK).body(movieDtos);
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
