package com.bookmymovie.api.bookingapp.dto;

import lombok.Data;

@Data
public class PaymentDto {

    private Double amount;

    private String currency;

    private String receiptEmail;
}
