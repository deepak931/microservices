package com.bookmymovie.api.bookingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SeatNotAvailableException extends RuntimeException {

    public SeatNotAvailableException(String message) {
        super(message);
    }
}
