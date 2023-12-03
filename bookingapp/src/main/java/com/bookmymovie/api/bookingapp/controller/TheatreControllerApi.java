package com.bookmymovie.api.bookingapp.controller;

import com.bookmymovie.api.bookingapp.dto.ResponseDto;
import com.bookmymovie.api.bookingapp.dto.TheatreDto;
import com.bookmymovie.api.bookingapp.dto.TheatreRequestDto;
import com.bookmymovie.api.bookingapp.service.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookmymovie.api.bookingapp.constants.BookingAppConstants.messageCreated;

@RestController
@RequestMapping(path = "/bookmymovie/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TheatreControllerApi {

    private final TheatreService theatreService;


    public TheatreControllerApi(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping("/theatre")
    public ResponseEntity<ResponseDto> createTheatre(@RequestBody TheatreRequestDto theatreDto) {

        Long id = theatreService.creatTheatre(theatreDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.name(), messageCreated("Theatre", id.toString())));


    }

    @GetMapping("/theatres")
    public ResponseEntity<List<TheatreDto>> getAllTheatres(@RequestParam(defaultValue = "false") Boolean seats) {
        List<TheatreDto> theatreDtos = theatreService.getAllTheatre(seats);
        return ResponseEntity.status(HttpStatus.OK).body(theatreDtos);

    }


    @GetMapping("/theatres/{id}")
    public ResponseEntity<TheatreDto> getTheatre(@PathVariable Long id) {
        TheatreDto theatreDto = theatreService.getTheatreById(id);
        return ResponseEntity.status(HttpStatus.OK).body(theatreDto);

    }


}
