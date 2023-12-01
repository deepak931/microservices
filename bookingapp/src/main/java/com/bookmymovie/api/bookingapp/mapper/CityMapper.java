package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.CityDto;
import com.bookmymovie.api.bookingapp.entity.City;

public class CityMapper {

  public static City mapToCity(CityDto cityDto, City city) {

    return city;
  }

  public static CityDto mapToCityDto(City city, CityDto cityDto) {

    return cityDto;
  }
}
