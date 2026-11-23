package queuedesk.service;

import queuedesk.TicketManager.Ticket;
import queuedesk.expectionPackage.InvalidTicketDataException;
import queuedesk.loader.TicketFileLoader;
import queuedesk.ticketRepository.TicketSink;

import java.util.List;

public class TicketService {
    // This class is responsible for managing tickets, including creating, updating, and retrieving tickets from the repository

    // Method to load tickets from a file
    private List<Ticket> tickets;
    public void loadFile(String pathToTicketsFile) throws InvalidTicketDataException {
        tickets = TicketFileLoader.load(pathToTicketsFile);
    }
    //now create a repository from the tickets obtained above
    //repository me we have private list of ticket ke objects
    //this should not be like this object to pass hona chahiye
    public void createRepository(TicketSink<Ticket> ticketRepository) {
        if(tickets==null){
            throw new IllegalStateException("Tickets have not been loaded. Please load tickets before creating a repository.");
        }
        for (Ticket ticket : tickets) {
            ticketRepository.add(ticket);
        }
    }

}
