package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.dto.TheatreDto;
import com.bookmymovie.api.bookingapp.dto.TheatreRequestDto;
import com.bookmymovie.api.bookingapp.entity.City;
import com.bookmymovie.api.bookingapp.entity.Partner;
import com.bookmymovie.api.bookingapp.entity.Seat;
import com.bookmymovie.api.bookingapp.entity.Theatre;
import com.bookmymovie.api.bookingapp.exception.ResourceNotFoundExcption;
import com.bookmymovie.api.bookingapp.mapper.SeatMapper;
import com.bookmymovie.api.bookingapp.mapper.TheatreMapper;
import com.bookmymovie.api.bookingapp.repository.CityRepository;
import com.bookmymovie.api.bookingapp.repository.PartnerOnboardingRepository;
import com.bookmymovie.api.bookingapp.repository.TheatreRepository;
import com.bookmymovie.api.bookingapp.service.TheatreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepository;

    private final PartnerOnboardingRepository partnerOnboardingRepository;

    private final CityRepository cityRepository;

    public TheatreServiceImpl(TheatreRepository theatreRepository,
                              PartnerOnboardingRepository partnerOnboardingRepository, CityRepository cityRepository) {
        this.theatreRepository = theatreRepository;
        this.partnerOnboardingRepository = partnerOnboardingRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public Long creatTheatre(TheatreRequestDto theatreDto) {
        Partner partner = partnerOnboardingRepository.findById(theatreDto.getPartnerId()).orElseThrow(
                () -> new ResourceNotFoundExcption("Partner", "partnerId",
                        String.valueOf(theatreDto.getPartnerId())));

        City city = cityRepository.findById(theatreDto.getCityId())
                .orElseThrow(() -> new ResourceNotFoundExcption("City", "cityId", theatreDto.getCityId().toString()));
        Theatre theatre = TheatreMapper.mapToTheatre(theatreDto, new Theatre());
        theatre.setPartner(partner);
        theatre.setCity(city);

        Set<Seat> seats = theatreDto.getSeats().stream().map(e -> SeatMapper.mapToSeat(e, new Seat()))
                .collect(Collectors.toSet());
        theatre.setSeats(seats);
        return theatreRepository.save(theatre).getTheatreId();

    }

    @Override
    public List<TheatreDto> getAllTheatre(boolean isSeats) {
        List<Theatre> theatres = theatreRepository.findAll();
        return theatres.stream().map(e -> TheatreMapper.mapToTheatreDto(e, new TheatreDto(), isSeats, true))
                .collect(Collectors.toList());
    }

    @Override
    public TheatreDto getTheatreById(Long id) {
        Theatre theatre = theatreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExcption("Theatre", "theatreID", id.toString()));
        return TheatreMapper.mapToTheatreDto(theatre, new TheatreDto(), true, true);
    }
}
