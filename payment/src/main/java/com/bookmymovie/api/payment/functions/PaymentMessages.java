package com.bookmymovie.api.payment.functions;

import com.bookmymovie.api.payment.controller.PaymentService;
import com.bookmymovie.api.payment.dto.MovieBookingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;



@Configuration
public class PaymentMessages {

  private static final Logger logger= LoggerFactory.getLogger(PaymentMessages.class);
    @Value("${payment.gateway.url}")
    private String paymentGatewayUrl;


    @Autowired
  private PaymentService paymentService;

@Bean
  public Function<MovieBookingDto,MovieBookingDto> payment(){
     return movieBookingDto -> {
       logger.info("Processing payment for movie with booking id="+movieBookingDto.getBookingId());
         paymentService.callPayment(movieBookingDto,paymentGatewayUrl);
       return movieBookingDto;
     };
   }


}
