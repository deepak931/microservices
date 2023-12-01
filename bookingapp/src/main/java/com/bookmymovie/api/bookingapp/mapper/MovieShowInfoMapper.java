package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.MovieShowDto;
import com.bookmymovie.api.bookingapp.dto.MovieShowInfoDto;
import com.bookmymovie.api.bookingapp.dto.MovieTheatreDto;
import com.bookmymovie.api.bookingapp.dto.SeatDto;
import com.bookmymovie.api.bookingapp.entity.Movie;
import com.bookmymovie.api.bookingapp.entity.Seat;
import com.bookmymovie.api.bookingapp.entity.Shows;
import com.bookmymovie.api.bookingapp.entity.Theatre;

import java.util.List;

public class MovieShowInfoMapper {

  private static MovieShowInfoDto createMovieShowInfo(Movie movie) {
    MovieShowInfoDto movieShowInfoDto = new MovieShowInfoDto();
    movieShowInfoDto.setMovieId(movie.getMovieId());
    movieShowInfoDto.setGenre(movie.getGenre());
    movieShowInfoDto.setMovieTitle(movie.getTitle());
    movieShowInfoDto.setDirector(movie.getDirector());

    List<MovieTheatreDto> list =
        movie.getShows().stream().map(e -> createTheatre(e.getTheatre(), e)).toList();

    movieShowInfoDto.setTheatres(list);

    return movieShowInfoDto;

  }

  private static MovieTheatreDto createTheatre(Theatre theatre, Shows shows) {
    MovieTheatreDto movieTheatreDto = new MovieTheatreDto();
    movieTheatreDto.setTheatreId(theatre.getTheatreId());
    movieTheatreDto.setTheatreName(theatre.getTheatreName());
    movieTheatreDto.setCity(theatre.getCity().getName());
    movieTheatreDto.setAddress(theatre.getAddress());

    MovieShowDto movieShowDto = createShowInfo(shows, theatre);

    movieTheatreDto.getShowsDtoList().add(movieShowDto);

    return movieTheatreDto;
  }

  private static MovieShowDto createShowInfo(Shows shows, Theatre theatre) {
    MovieShowDto movieShowDto = new MovieShowDto();
    movieShowDto.setShowId(shows.getShowId());
    movieShowDto.setStartTime(shows.getStartTime());
    movieShowDto.setEndTime(shows.getEndTime());

    List<SeatDto> totalSeats =
        theatre.getSeats().stream().map(e -> SeatMapper.mapToSeatDto(e, new SeatDto())).toList();
    List<Long> bookedSeats =
        shows.getTickets().stream().flatMap(e -> e.getSeats().stream()).map(Seat::getSeatId)
            .toList();
    List<SeatDto> availableSeats =
        totalSeats.stream().dropWhile(e -> bookedSeats.contains(e.getSeatId())).toList();

    movieShowDto.setSeatDtos(availableSeats);
    return movieShowDto;
  }

  
}
