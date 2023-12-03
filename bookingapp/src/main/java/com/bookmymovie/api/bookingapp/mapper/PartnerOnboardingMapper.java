package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.PartnerDto;
import com.bookmymovie.api.bookingapp.entity.Partner;

public class PartnerOnboardingMapper {

    public static PartnerDto mapToPartnerOnboardingDto(Partner partnerOnboarding,
                                                       PartnerDto partnerOnboardingDto) {
        partnerOnboardingDto.setName(partnerOnboarding.getName());
        partnerOnboardingDto.setEmail(partnerOnboarding.getEmail());
        partnerOnboardingDto.setMobileNumber(partnerOnboarding.getMobileNumber());
        partnerOnboardingDto.setAddress(partnerOnboarding.getAddress());
        partnerOnboardingDto.setPartnerId(partnerOnboarding.getPartnerID());
        partnerOnboardingDto.setCityId(partnerOnboarding.getCity().getCityId());
        partnerOnboardingDto.setCityName(partnerOnboarding.getCity().getName());

        return partnerOnboardingDto;
    }

    public static Partner mapToPartnerOnboarding(PartnerDto partnerOnboardingDto,
                                                 Partner partnerOnboarding) {
        partnerOnboarding.setName(partnerOnboardingDto.getName());
        partnerOnboarding.setEmail(partnerOnboardingDto.getEmail());
        partnerOnboarding.setPartnerID(partnerOnboardingDto.getPartnerId());
        partnerOnboarding.setMobileNumber(partnerOnboardingDto.getMobileNumber());
        partnerOnboarding.setAddress(partnerOnboardingDto.getAddress());
        return partnerOnboarding;
    }
}
