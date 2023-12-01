package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowsRepository extends JpaRepository<Shows, Long> {

}
