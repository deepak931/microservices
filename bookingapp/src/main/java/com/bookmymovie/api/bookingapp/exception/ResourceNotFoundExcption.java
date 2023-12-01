package com.bookmymovie.api.bookingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExcption extends RuntimeException {

  public ResourceNotFoundExcption(String resourceName, String fieldName, String fieldValue) {
    super(
        String.format("%s not found with input data %s: %s", resourceName, fieldName, fieldValue));
  }
}
