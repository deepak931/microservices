package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.repository.SeatReservationRepository;
import com.bookmymovie.api.bookingapp.service.SeatReservationService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SeatReservationServiceImpl implements SeatReservationService {

    private SeatReservationRepository seatReservationRepository;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    @Override
    public void deleteSeatReservation(Long seatReservationID) {
        seatReservationRepository.deleteById(seatReservationID);

    }
}
