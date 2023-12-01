package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.ShowsDto;
import com.bookmymovie.api.bookingapp.entity.Shows;

public class ShowsMapper {

  public static Shows mapToShows(ShowsDto showsDto, Shows shows) {
    shows.setEndTime(showsDto.getEndTime());
    shows.setStartTime(showsDto.getStartTime());
    return shows;
  }

  public static ShowsDto mapToShowsDto(Shows shows, ShowsDto showsDto) {
    showsDto.setEndTime(shows.getEndTime());
    showsDto.setStartTime(shows.getStartTime());
    showsDto.setShowId(shows.getShowId());
    return showsDto;
  }

}
