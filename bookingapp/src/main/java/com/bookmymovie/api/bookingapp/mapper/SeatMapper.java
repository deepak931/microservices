package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.SeatDto;
import com.bookmymovie.api.bookingapp.entity.Seat;

public class SeatMapper {

    public static Seat mapToSeat(SeatDto seatDto, Seat seat) {
        seat.setSeatNo(seatDto.getSeatNo());
        seat.setType(seatDto.getSeatType());
        return seat;
    }

    public static SeatDto mapToSeatDto(Seat seat, SeatDto seatDto) {
        seatDto.setSeatId(seat.getSeatId());
        seatDto.setSeatNo(seat.getSeatNo());
        seatDto.setSeatType(seat.getType());
        return seatDto;
    }
}
