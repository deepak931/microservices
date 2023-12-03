package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.PartnerDto;
import com.bookmymovie.api.bookingapp.dto.SeatDto;
import com.bookmymovie.api.bookingapp.dto.TheatreDto;
import com.bookmymovie.api.bookingapp.dto.TheatreRequestDto;
import com.bookmymovie.api.bookingapp.entity.Theatre;

import java.util.Set;
import java.util.stream.Collectors;

public class TheatreMapper {

    public static Theatre mapToTheatre(TheatreDto theatreDto, Theatre theatre) {
        theatre.setTheatreId(theatreDto.getTheatreId());
        theatre.setTheatreEmail(theatreDto.getTheatreEmail());
        theatre.setTheatreName(theatreDto.getTheatreName());
        theatre.setAddress(theatreDto.getAddress());
        theatre.setContactNumber(theatreDto.getContactNumber());
        return theatre;
    }

    public static Theatre mapToTheatre(TheatreRequestDto theatreDto, Theatre theatre) {
        theatre.setTheatreEmail(theatreDto.getTheatreEmail());
        theatre.setTheatreName(theatreDto.getTheatreName());
        theatre.setAddress(theatreDto.getAddress());
        theatre.setContactNumber(theatreDto.getContactNumber());
        return theatre;
    }

    public static TheatreDto mapToTheatreDto(Theatre theatre, TheatreDto theatreDto, boolean isSeats,
                                             boolean isPartner) {
        theatreDto.setTheatreId(theatre.getTheatreId());
        theatreDto.setTheatreEmail(theatre.getTheatreEmail());
        theatreDto.setTheatreName(theatre.getTheatreName());
        theatreDto.setAddress(theatre.getAddress());
        theatreDto.setCity(theatre.getCity().getName());
        theatreDto.setContactNumber(theatre.getContactNumber());

        if (isPartner) {
            PartnerDto partnerDto =
                    PartnerOnboardingMapper.mapToPartnerOnboardingDto(theatre.getPartner(), new PartnerDto());
            theatreDto.setPartnerDto(partnerDto);
        }

        if (isSeats) {


            Set<SeatDto> collect =
                    theatre.getSeats().stream().map(e -> SeatMapper.mapToSeatDto(e, new SeatDto()))
                            .collect(Collectors.toSet());
            theatreDto.setSeats(collect);
        }
        return theatreDto;
    }
}
