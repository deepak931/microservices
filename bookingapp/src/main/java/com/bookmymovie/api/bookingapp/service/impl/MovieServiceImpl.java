package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.dto.MovieDto;
import com.bookmymovie.api.bookingapp.entity.Movie;
import com.bookmymovie.api.bookingapp.entity.SeatReservation;
import com.bookmymovie.api.bookingapp.entity.Shows;
import com.bookmymovie.api.bookingapp.entity.Theatre;
import com.bookmymovie.api.bookingapp.exception.ResourceNotFoundExcption;
import com.bookmymovie.api.bookingapp.mapper.MovieMapper;
import com.bookmymovie.api.bookingapp.mapper.ShowsMapper;
import com.bookmymovie.api.bookingapp.repository.MovieRepository;
import com.bookmymovie.api.bookingapp.repository.SeatReservationRepository;
import com.bookmymovie.api.bookingapp.repository.TheatreRepository;
import com.bookmymovie.api.bookingapp.repository.TicketRepository;
import com.bookmymovie.api.bookingapp.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

  private final TheatreRepository theatreRepository;

  private final MovieRepository movieRepository;

  private final TicketRepository ticketRepository;

  private final SeatReservationRepository seatReservationRepository;

  @Override
  public void creatMovie(MovieDto movieDto) {

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
    movieRepository.save(movie);

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
  public List<MovieDto> getMoviesByNameAndCity(String name, String city) {
    List<Movie> movies = movieRepository.findByMovieNameAndCity(name, city);
    Set<Long> movieIds = movies.stream().map(Movie::getMovieId).collect(Collectors.toSet());
    //List<Ticket> bookedShows = ticketRepository.findByShowsAndMovie(movieIds);
    List<SeatReservation> sr = seatReservationRepository.findSeatReservationByMovieIn(movieIds);

    return movies.stream().map(e -> MovieMapper.mapToMovieDto(e, new MovieDto()))
        .collect(Collectors.toList());
  }


}
