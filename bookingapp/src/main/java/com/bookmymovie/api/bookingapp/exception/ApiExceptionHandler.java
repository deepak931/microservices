package com.bookmymovie.api.bookingapp.exception;


import com.bookmymovie.api.bookingapp.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(PartnerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handlePartnerAlreadyExistsException(
            PartnerAlreadyExistsException ex, WebRequest webRequest) {
        ErrorResponseDto error =
                new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                        ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundExcption.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundExcption ex, WebRequest webRequest) {
        ErrorResponseDto error =
                new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.NOT_FOUND,
                        ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShowsTimeOverlapException.class)
    public ResponseEntity<ErrorResponseDto> handleShowsTimeOverlapException(
            ShowsTimeOverlapException ex, WebRequest webRequest) {
        ErrorResponseDto error =
                new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                        ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(
            UserAlreadyExistsException ex, WebRequest webRequest) {
        ErrorResponseDto error =
                new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                        ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<ErrorResponseDto> handleSeatNotAvailableException(
            ResourceNotFoundExcption ex, WebRequest webRequest) {
        ErrorResponseDto error =
                new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                        ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CreateMovieException.class)
    public ResponseEntity<ErrorResponseDto> handleCreateMovieException(
            CreateMovieException ex, WebRequest webRequest) {
        ErrorResponseDto error =
                new ErrorResponseDto(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                        ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
