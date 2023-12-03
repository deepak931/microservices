package com.bookmymovie.api.bookingapp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PartnerDto {

    private Long clientId;

    @NotEmpty(message = "Partner name can not be null or empty")
    @Size(min = 6, max = 200, message = "The length of the Partner name should be 6 and 200 chars")
    private String name;

    @NotEmpty(message = "Partner email can not be null or empty")
    @Email(message = "Email value should be a valid value")
    private String email;

    @NotEmpty(message = "Partner mobile no can not be null or empty")
    private String mobileNumber;

    private String address;

    @NotEmpty(message = "Partner city id can not be null or empty")
    private Long cityId;
}
