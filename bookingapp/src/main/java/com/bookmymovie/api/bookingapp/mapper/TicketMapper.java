package com.bookmymovie.api.bookingapp.mapper;

import com.bookmymovie.api.bookingapp.dto.TicketDto;
import com.bookmymovie.api.bookingapp.entity.Ticket;

public class TicketMapper {

  public static Ticket mapToTicket(TicketDto ticketDto, Ticket ticket) {
    return ticket;
  }

  public static TicketDto mapToTicketDto(Ticket ticket, TicketDto ticketDto) {
    return ticketDto;
  }
}
