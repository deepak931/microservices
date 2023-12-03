package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.MovieDto;
import com.bookmymovie.api.bookingapp.dto.MovieRequestDto;
import com.bookmymovie.api.bookingapp.dto.ShowsDto;
import com.bookmymovie.api.bookingapp.dto.TheatreDto;
import com.bookmymovie.api.bookingapp.entity.Movie;
import com.bookmymovie.api.bookingapp.entity.Shows;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieMapper {

    public static Movie mapToMovie(MovieDto movieDto, Movie movie) {
        movie.setDirector(movieDto.getDirector());
        movie.setGenre(movieDto.getGenre());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setTitle(movieDto.getTitle());
        Set<Shows> shows = movieDto.getShows().stream().map(e -> ShowsMapper.mapToShows(e, new Shows()))
                .collect(Collectors.toSet());
        movie.setShows(shows);


        return movie;
    }

    public static Movie mapToMovie(MovieRequestDto movieDto, Movie movie) {
        movie.setDirector(movieDto.getDirector());
        movie.setGenre(movieDto.getGenre());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setTitle(movieDto.getTitle());
        movie.setStartDate(movieDto.getStartDate());
        movie.setEndDate(movieDto.getEndDate());
        movie.setRunning(movie.isRunning());
        Set<Shows> shows = movieDto.getShows().stream().map(e -> ShowsMapper.mapToShows(e, new Shows()))
                .collect(Collectors.toSet());
        movie.setShows(shows);


        return movie;
    }

    public static MovieDto mapToMovieDto(Movie movie, MovieDto movieDto) {
        movieDto.setDirector(movie.getDirector());
        movieDto.setGenre(movie.getGenre());
        movieDto.setReleaseDate(movie.getReleaseDate());
        movieDto.setTitle(movie.getTitle());
        movieDto.setMovieId(movie.getMovieId());
        Set<ShowsDto> shows =
                movie.getShows().stream().map(e -> ShowsMapper.mapToShowsDto(e, new ShowsDto()))
                        .collect(Collectors.toSet());
        movieDto.setShows(shows);

        Set<TheatreDto> theatreDtos = new HashSet<>();
        TheatreDto theatreDto = TheatreMapper.mapToTheatreDto(movie.getTheatres(), new TheatreDto(), true, false);
        theatreDtos.add(theatreDto);
        movieDto.setTheatres(theatreDtos);
        return movieDto;
    }
}
