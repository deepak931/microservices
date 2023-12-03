package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String cityName);


}
