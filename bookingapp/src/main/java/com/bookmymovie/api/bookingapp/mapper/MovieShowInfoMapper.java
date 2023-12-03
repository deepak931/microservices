package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.MovieShowDto;
import com.bookmymovie.api.bookingapp.dto.MovieShowInfoDto;
import com.bookmymovie.api.bookingapp.dto.MovieTheatreDto;
import com.bookmymovie.api.bookingapp.dto.SeatDto;
import com.bookmymovie.api.bookingapp.entity.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieShowInfoMapper {

  public static MovieShowInfoDto createMovieShowInfo(Movie movie, LocalDate date,
      Map<Long, List<SeatReservation>> reservedSeats) {
    MovieShowInfoDto movieShowInfoDto = new MovieShowInfoDto();
    movieShowInfoDto.setMovieId(movie.getMovieId());
    movieShowInfoDto.setGenre(movie.getGenre());
    movieShowInfoDto.setMovieTitle(movie.getTitle());
    movieShowInfoDto.setDirector(movie.getDirector());

    List<MovieTheatreDto> list = movie.getShows().stream()
        .map(e -> createTheatre(e.getTheatre(), e, date, reservedSeats.get(e.getShowId())))
        .toList();

    movieShowInfoDto.setTheatres(list);

    return movieShowInfoDto;

  }

  public static Map<Long, Map<Long, List<SeatReservation>>> reservedSeatsForMovie(
      List<SeatReservation> reservedSeats) {

    return reservedSeats.stream().collect(Collectors.groupingBy(e -> e.getMovie().getMovieId(),
        Collectors.groupingBy(e -> e.getShows().getShowId())));

  }


  private static MovieTheatreDto createTheatre(Theatre theatre, Shows shows, LocalDate date,
      List<SeatReservation> reservedSeats) {
    MovieTheatreDto movieTheatreDto = new MovieTheatreDto();
    movieTheatreDto.setTheatreId(theatre.getTheatreId());
    movieTheatreDto.setTheatreName(theatre.getTheatreName());
    movieTheatreDto.setCity(theatre.getCity().getName());
    movieTheatreDto.setAddress(theatre.getAddress());

    MovieShowDto movieShowDto = createShowInfo(shows, theatre, date, reservedSeats);

    movieTheatreDto.getShowsDtoList().add(movieShowDto);

    return movieTheatreDto;
  }

  private static MovieShowDto createShowInfo(Shows shows, Theatre theatre, LocalDate date,
      List<SeatReservation> reservedSeats) {
    MovieShowDto movieShowDto = new MovieShowDto();
    movieShowDto.setShowId(shows.getShowId());
    movieShowDto.setStartTime(shows.getStartTime());
    movieShowDto.setEndTime(shows.getEndTime());

    List<SeatDto> totalSeats =
        theatre.getSeats().stream().map(e -> SeatMapper.mapToSeatDto(e, new SeatDto())).toList();
    List<Long> bookedSeats = shows.getTickets().stream().filter(e -> e.getShowDate().equals(date))
        .flatMap(e -> e.getSeats().stream()).map(Seat::getSeatId).toList();



    List<SeatDto> availableSeats =
        totalSeats.stream().dropWhile(e -> bookedSeats.contains(e.getSeatId())).toList();
    
    if (reservedSeats != null && !reservedSeats.isEmpty()) {
      List<Long> reservedSeatIDs =
          reservedSeats.stream().flatMap(e -> Arrays.stream(e.getSeatIds().split(",")))
              .map(Long::valueOf).toList();
      availableSeats =
          availableSeats.stream().dropWhile(e -> reservedSeatIDs.contains(e.getSeatId())).toList();

    }

    movieShowDto.setSeatDtos(availableSeats);
    return movieShowDto;
  }


}
