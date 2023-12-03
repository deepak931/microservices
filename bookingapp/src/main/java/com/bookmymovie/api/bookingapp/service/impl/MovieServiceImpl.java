package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.dto.MovieDto;
import com.bookmymovie.api.bookingapp.dto.MovieRequestDto;
import com.bookmymovie.api.bookingapp.dto.MovieShowInfoDto;
import com.bookmymovie.api.bookingapp.entity.Movie;
import com.bookmymovie.api.bookingapp.entity.SeatReservation;
import com.bookmymovie.api.bookingapp.entity.Shows;
import com.bookmymovie.api.bookingapp.entity.Theatre;
import com.bookmymovie.api.bookingapp.exception.ResourceNotFoundExcption;
import com.bookmymovie.api.bookingapp.mapper.MovieMapper;
import com.bookmymovie.api.bookingapp.mapper.MovieShowInfoMapper;
import com.bookmymovie.api.bookingapp.mapper.ShowsMapper;
import com.bookmymovie.api.bookingapp.repository.MovieRepository;
import com.bookmymovie.api.bookingapp.repository.SeatReservationRepository;
import com.bookmymovie.api.bookingapp.repository.TheatreRepository;
import com.bookmymovie.api.bookingapp.repository.TicketRepository;
import com.bookmymovie.api.bookingapp.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final TheatreRepository theatreRepository;

    private final MovieRepository movieRepository;

    private final TicketRepository ticketRepository;

    private final SeatReservationRepository seatReservationRepository;

    private final StreamBridge streamBridge;

    @Override
    public Long creatMovie(MovieRequestDto movieDto) {

        Theatre theatre = theatreRepository.findById(movieDto.getTheatreId()).orElseThrow(
                () -> new ResourceNotFoundExcption("Theatre", "theatreId",
                        movieDto.getTheatreId().toString()));

        Movie movie = MovieMapper.mapToMovie(movieDto, new Movie());

        Set<Shows> shows = movieDto.getShows().stream().map(e -> ShowsMapper.mapToShows(e, new Shows()))
                .collect(Collectors.toSet());
        Set<Theatre> theatreList = new HashSet<>();
        theatreList.add(theatre);
        movie.setTheatres(theatreList);
        movie.setShows(shows);
        return movieRepository.save(movie).getMovieId();


    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(e -> MovieMapper.mapToMovieDto(e, new MovieDto()))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcption("Movie", "movieId", id.toString()));
        return MovieMapper.mapToMovieDto(movie, new MovieDto());

    }

    @Override
    public List<MovieShowInfoDto> getMoviesByNameAndCity(String name, String city, LocalDate date) {
        List<Movie> movies = movieRepository.findByMovieNameAndCity(name, city);
        Set<Long> movieIds = movies.stream().map(Movie::getMovieId).collect(Collectors.toSet());
        List<SeatReservation> sr =
                seatReservationRepository.findSeatReservationByDateAndMovieIn(date, movieIds);

        Map<Long, Map<Long, List<SeatReservation>>> reservedSeatsForMovie =
                MovieShowInfoMapper.reservedSeatsForMovie(sr);

        return movies.stream().map(e -> MovieShowInfoMapper.createMovieShowInfo(e, date,
                reservedSeatsForMovie.get(e.getMovieId()))).collect(Collectors.toList());
    }

    @Override
    public void test(MovieBookingDto movieDto) {
        sendCommunication(movieDto);
    }

    private void sendCommunication(MovieBookingDto movieDto) {
        MovieBookingDto movieBookingDto = new MovieBookingDto();
        movieBookingDto.setBookingId(109L);
        movieBookingDto.setStatus("SUCCESS");
        movieBookingDto.setAmount(200d);
        movieBookingDto.setSeatReservationId(123l);
        streamBridge.send("sendPaymentReq-out-0", movieDto);
    }
}
