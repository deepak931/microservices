package com.bookmymovie.api.bookingapp.controller;

import com.bookmymovie.api.bookingapp.constants.BookingAppConstants;
import com.bookmymovie.api.bookingapp.dto.ResponseDto;
import com.bookmymovie.api.bookingapp.dto.TheatreDto;
import com.bookmymovie.api.bookingapp.service.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TheatreController {

  private final TheatreService theatreService;


  public TheatreController(TheatreService theatreService) {
    this.theatreService = theatreService;
  }

  @PostMapping("/theatre")
  public ResponseEntity<ResponseDto> createTheatre(@RequestBody TheatreDto theatreDto) {

    theatreService.creatTheatre(theatreDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(HttpStatus.CREATED.name(), BookingAppConstants.MESSAGE_201));


  }

  @GetMapping("/theatres")
  public ResponseEntity<List<TheatreDto>> getAllTheatres(@RequestParam Boolean seats) {
    List<TheatreDto> theatreDtos = theatreService.getAllTheatre();
    return ResponseEntity.status(HttpStatus.OK).body(theatreDtos);

  }


  @GetMapping("/theatres/{id}")
  public ResponseEntity<TheatreDto> getTheatre(@PathVariable Long id) {
    TheatreDto theatreDto = theatreService.getTheatreById(id);
    return ResponseEntity.status(HttpStatus.OK).body(theatreDto);

  }


}
