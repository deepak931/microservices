package com.bookmymovie.api.payment.controller;


import com.bookmymovie.api.payment.dto.MovieBookingDto;
import com.bookmymovie.api.payment.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bookmymovie/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;
    @PostMapping("/payment")
    public ResponseEntity<ResponseDto> processPayment(@RequestBody MovieBookingDto movieBookingDto) {
        movieBookingDto.setStatus("SUCCESS");
        paymentService.notifyPayment(movieBookingDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.name(), "Payment processed"));

    }
}
