package com.bookmymovie.api.payment.controller;

import com.bookmymovie.api.payment.dto.MovieBookingDto;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PaymentService {

    private StreamBridge streamBridge;

    public void notifyPayment(MovieBookingDto movieBookingDto) {
//        MovieBookingDto movieBookingDto = new MovieBookingDto();
//        movieBookingDto.setBookingId(657l);
//        movieBookingDto.setStatus("SUCCESS");
//        movieBookingDto.setAmount(200d);
//        movieBookingDto.setSeatReservationId(879l);
        streamBridge.send("sendProcessBookingReq-out-0", movieBookingDto);

    }

    public void callPayment(MovieBookingDto movieBookingDto,String paymentGatewayUrl){
        RestTemplate restTemplate = new RestTemplate();
        String paymentUrl
                = "http://localhost:9090/bookmymovie/api/payment";

        ResponseEntity<String> response= restTemplate.postForEntity(paymentGatewayUrl,movieBookingDto,String.class);
    }

}
