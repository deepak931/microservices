package com.bookmymovie.api.bookingapp.controller;

import com.bookmymovie.api.bookingapp.constants.BookingAppConstants;
import com.bookmymovie.api.bookingapp.dto.PartnerDto;
import com.bookmymovie.api.bookingapp.dto.ResponseDto;
import com.bookmymovie.api.bookingapp.service.PartnerOnboardingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartnerOnboardingApi {

  private final PartnerOnboardingService partnerOnboardingService;

  public PartnerOnboardingApi(PartnerOnboardingService partnerOnboardingService) {
    this.partnerOnboardingService = partnerOnboardingService;
  }

  @PostMapping("/onboarding")
  public ResponseEntity<ResponseDto> createPartnerOnboarding(
      @RequestBody PartnerDto partnerOnboardingDto) {
    partnerOnboardingService.createPartnerOnboarding(partnerOnboardingDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(HttpStatus.CREATED.name(), BookingAppConstants.MESSAGE_201));
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
