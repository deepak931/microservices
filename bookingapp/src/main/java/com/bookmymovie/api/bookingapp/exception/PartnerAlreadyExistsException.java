package com.bookmymovie.api.bookingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PartnerAlreadyExistsException extends RuntimeException {

  public PartnerAlreadyExistsException(String message) {
    super(message);
  }
}
