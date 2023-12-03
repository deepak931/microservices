package com.bookmymovie.api.bookingapp.controller;

import com.bookmymovie.api.bookingapp.dto.PartnerDto;
import com.bookmymovie.api.bookingapp.dto.ResponseDto;
import com.bookmymovie.api.bookingapp.service.PartnerOnboardingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bookmymovie.api.bookingapp.constants.BookingAppConstants.messageCreated;

@RestController
@RequestMapping(path = "/bookmymovie/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnerOnboardingApi {

    private final PartnerOnboardingService partnerOnboardingService;

    public PartnerOnboardingApi(PartnerOnboardingService partnerOnboardingService) {
        this.partnerOnboardingService = partnerOnboardingService;
    }

    @PostMapping("/partner/onboarding")
    public ResponseEntity<ResponseDto> createPartnerOnboarding(
            @RequestBody PartnerDto partnerOnboardingDto) {
        Long id = partnerOnboardingService.createPartnerOnboarding(partnerOnboardingDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.name(), messageCreated("Partner", id.toString())));
    }

    @GetMapping("/partners")
    public ResponseEntity<List<PartnerDto>> getAllPartners() {
        List<PartnerDto> list = partnerOnboardingService.getAllPartners();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/partners/{id}")
    public ResponseEntity<PartnerDto> getPartner(@PathVariable Long id) {
        PartnerDto partnerOnboarding = partnerOnboardingService.getPartnerByClientId(id);
        return ResponseEntity.status(HttpStatus.OK).body(partnerOnboarding);
    }


}
