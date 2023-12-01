package com.bookmymovie.api.bookingapp.repository;


import com.bookmymovie.api.bookingapp.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerOnboardingRepository extends JpaRepository<Partner, Long> {
  Optional<Partner> findByMobileNumber(String mobileNumber);
}
