package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
