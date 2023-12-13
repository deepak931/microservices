package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.PriceDto;
import com.bookmymovie.api.bookingapp.entity.Price;

public class PriceMapper {

    public static Price mapToPrice(PriceDto priceDto, Price price) {
        price.setAmount(priceDto.getAmount());
        price.setSeatType(priceDto.getSeatType());
        return price;
    }

    public static PriceDto mapToPriceDto(Price price, PriceDto priceDto) {
        priceDto.setAmount(price.getAmount());
        priceDto.setSeatType(price.getSeatType());
        return priceDto;
    }
}
