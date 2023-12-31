package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.PartnerDto;

import java.util.List;

public interface PartnerOnboardingService {

    /**
     *
     */
    Long createPartnerOnboarding(PartnerDto partnerOnboardingDto);

    List<PartnerDto> getAllPartners();

    PartnerDto getPartnerByClientId(Long clientId);
}
