package queuedesk.ticketRepository;

import queuedesk.TicketManager.Ticket;

// A small interface so TicketService depends on the add operation, not the concrete repository implementation.
public interface TicketSink<T extends Ticket> {
    void add(T ticket);
}
