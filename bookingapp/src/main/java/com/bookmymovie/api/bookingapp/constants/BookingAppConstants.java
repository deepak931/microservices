package com.bookmymovie.api.bookingapp.constants;

public class BookingAppConstants {

    public static final String MESSAGE_201 = "%s created successfully with id %s";

    public static String messageCreated(String... args) {
        return String.format(MESSAGE_201, args[0], args[1]);
    }
}
