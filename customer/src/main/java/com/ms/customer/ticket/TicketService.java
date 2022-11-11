package com.ms.customer.ticket;

import com.ms.customer.shared.exceptions.NotFound;
import com.ms.customer.ticket.dto.TicketCreationDto;
import com.ms.customer.ticket.dto.TicketDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record TicketService(TicketRepository ticketRepository) {

    public List<Ticket> getAll() {
        return this.ticketRepository.findAll();
    }

    public Ticket getTicketById(String ticketId) {
        return this.ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFound(String.format("Ticket with id %s not found.", ticketId)));
    }

    public Ticket createTicket(TicketCreationDto ticketCreationDto) {
        Ticket ticket = Ticket.builder()
                .price(ticketCreationDto.price())
                .build();
        return this.ticketRepository.save(ticket);
    }

    public Ticket updateTicket(String ticketId, TicketDto ticketDto) {
        Ticket ticket = this.getTicketById(ticketId);
        ticket.setPrice(ticketDto.price());
        return this.ticketRepository.save(ticket);
    }
}
