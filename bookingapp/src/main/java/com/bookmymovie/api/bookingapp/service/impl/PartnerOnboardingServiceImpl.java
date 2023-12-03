package com.bookmymovie.api.bookingapp.service.impl;

import com.bookmymovie.api.bookingapp.dto.PartnerDto;
import com.bookmymovie.api.bookingapp.entity.Partner;
import com.bookmymovie.api.bookingapp.exception.PartnerAlreadyExistsException;
import com.bookmymovie.api.bookingapp.exception.ResourceNotFoundExcption;
import com.bookmymovie.api.bookingapp.mapper.PartnerOnboardingMapper;
import com.bookmymovie.api.bookingapp.repository.CityRepository;
import com.bookmymovie.api.bookingapp.repository.PartnerOnboardingRepository;
import com.bookmymovie.api.bookingapp.service.PartnerOnboardingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PartnerOnboardingServiceImpl implements PartnerOnboardingService {

    private final PartnerOnboardingRepository partnerOnboardingRepository;

    private final CityRepository cityRepository;


    @Override
    public Long createPartnerOnboarding(PartnerDto partnerOnboardingDto) {
        Partner partnerOnboarding =
                PartnerOnboardingMapper.mapToPartnerOnboarding(partnerOnboardingDto, new Partner());
        Optional<Partner> existingPartner =
                partnerOnboardingRepository.findByMobileNumber(partnerOnboardingDto.getMobileNumber());
        if (existingPartner.isPresent()) {
            throw new PartnerAlreadyExistsException(
                    "Partner already onboarded with the mobile " + "number " + partnerOnboardingDto.getMobileNumber());
        }
        cityRepository.findById(partnerOnboardingDto.getCityId()).orElseThrow(() -> new ResourceNotFoundExcption("City",
                "cityId", partnerOnboardingDto.getCityId().toString()));
        Partner partner = partnerOnboardingRepository.save(partnerOnboarding);
        return partner.getPartnerID();

    }

    @Override
    public List<PartnerDto> getAllPartners() {
        List<Partner> list = partnerOnboardingRepository.findAll();
        return list.stream()
                .map(e -> PartnerOnboardingMapper.mapToPartnerOnboardingDto(e, new PartnerDto()))
                .collect(Collectors.toList());
    }

    @Override
    public PartnerDto getPartnerByClientId(Long clientId) {
        Optional<Partner> partnerOnboarding = partnerOnboardingRepository.findById(clientId);
        return partnerOnboarding.map(
                onboarding -> PartnerOnboardingMapper.mapToPartnerOnboardingDto(onboarding,
                        new PartnerDto())).orElseGet(PartnerDto::new);
    }
}
