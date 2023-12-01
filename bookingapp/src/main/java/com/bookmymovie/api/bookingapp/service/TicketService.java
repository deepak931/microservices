package com.bookmymovie.api.bookingapp.service;

import com.bookmymovie.api.bookingapp.dto.TicketDto;

import java.util.List;

public interface TicketService {

  /**
   * @param ticketDto
   */
  void creatTicket(TicketDto ticketDto);

  List<TicketDto> getAllTickets();

}
