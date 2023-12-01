package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

}
