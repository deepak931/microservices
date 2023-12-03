package com.bookmymovie.api.bookingapp.functions;

import com.bookmymovie.api.bookingapp.constants.BookingStatus;
import com.bookmymovie.api.bookingapp.dto.MovieBookingDto;
import com.bookmymovie.api.bookingapp.dto.TicketRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.function.Function;

@Configuration
public class BookingMessage {
    private static final Logger logger = LoggerFactory.getLogger(BookingMessage.class);
    @Value("${booking.ticket.url}")
    private String bookingTicketUrl;

    @Bean
    public Function<MovieBookingDto, MovieBookingDto> processBooking() {
        return movieBookingDto -> {

            callPayment(movieBookingDto);
            return movieBookingDto;
        };
    }

    public void callPayment(MovieBookingDto movieBookingDto) {
        RestTemplate restTemplate = new RestTemplate();
        TicketRequestDto ticketRequestDto = new TicketRequestDto();
        ticketRequestDto.setBookingId(movieBookingDto.getBookingId());
        ticketRequestDto.setSeatReservationId(movieBookingDto.getBookingId());
        ticketRequestDto.setStatus(BookingStatus.SUCCESS);
        ResponseEntity<String> response = restTemplate.postForEntity(bookingTicketUrl, ticketRequestDto, String.class);
        logger.info(response.getBody());
    }
}
