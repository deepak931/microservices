package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import com.bookmymovie.api.bookingapp.dto.*;
import com.bookmymovie.api.bookingapp.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

        List<PriceDto> prices = movie.getPrice().stream().map(e -> PriceMapper.mapToPriceDto(e, new PriceDto()))
                .toList();
        movieShowInfoDto.setPrices(prices);

        List<Shows> shows = new ArrayList<>(movie.getShows());
        MovieTheatreDto theatreDto = createTheatre(movie.getTheatres(), shows, date, reservedSeats);

        movieShowInfoDto.setTheatre(theatreDto);

        return movieShowInfoDto;

    }

    public static Map<Long, Map<Long, List<SeatReservation>>> reservedSeatsForMovie(
            List<SeatReservation> reservedSeats) {

        return reservedSeats.stream().collect(Collectors.groupingBy(e -> e.getMovie().getMovieId(),
                Collectors.groupingBy(e -> e.getShows().getShowId())));

    }


    private static List<SeatReservation> getReservedSeatsForShow(Map<Long, List<SeatReservation>> reservedSeatsMap,
                                                                 Long showID) {
        List<SeatReservation> reservedSeats = new ArrayList<>();
        if (reservedSeatsMap != null && !reservedSeatsMap.isEmpty()) {
            reservedSeats = reservedSeatsMap.get(showID);
        }
        return reservedSeats;
    }

    private static MovieTheatreDto createTheatre(Theatre theatre, List<Shows> shows, LocalDate date,
                                                 Map<Long, List<SeatReservation>> reservedSeatsMap) {
        MovieTheatreDto movieTheatreDto = new MovieTheatreDto();
        movieTheatreDto.setTheatreId(theatre.getTheatreId());
        movieTheatreDto.setTheatreName(theatre.getTheatreName());
        movieTheatreDto.setCity(theatre.getCity().getName());
        movieTheatreDto.setAddress(theatre.getAddress());


        List<MovieShowDto> showsList = shows.stream().map(e -> createShowInfo(e, theatre, date,
                getReservedSeatsForShow(reservedSeatsMap, e.getShowId()))).toList();

        movieTheatreDto.setShowsDtoList(showsList);

        return movieTheatreDto;
    }

    private static MovieShowDto createShowInfo(Shows shows, Theatre theatre, LocalDate date,
                                               List<SeatReservation> reservedSeats) {
        MovieShowDto movieShowDto = new MovieShowDto();
        movieShowDto.setShowId(shows.getShowId());
        movieShowDto.setStartTime(shows.getStartTime());
        movieShowDto.setEndTime(shows.getEndTime());

        List<SeatDto> totalSeats =
                new ArrayList<>(theatre.getSeats().stream().map(e -> SeatMapper.mapToSeatDto(e, new SeatDto())).toList());
        List<Long> bookedSeats = shows.getTickets().stream().filter(e -> BookingStatus.SUCCESS.equals(e.getStatus())).
                filter(e -> e.getShowDate().equals(date))
                .flatMap(e -> e.getSeats().stream()).map(Seat::getSeatId).toList();


        totalSeats.removeIf(e -> bookedSeats.contains(e.getSeatId()));

        if (reservedSeats != null && !reservedSeats.isEmpty()) {
            List<Long> reservedSeatIDs =
                    reservedSeats.stream().flatMap(e -> Arrays.stream(e.getSeatIds().split(",")))
                            .map(Long::valueOf).toList();
            totalSeats.removeIf(e -> reservedSeatIDs.contains(e.getSeatId()));

        }

        movieShowDto.setSeatDtos(totalSeats);
        return movieShowDto;
    }


}
