package queuedesk.service;

import queuedesk.expectionPackage.InvalidTicketDataException;
import queuedesk.loader.TicketFileLoader;

public class TicketService {
    // This class is responsible for managing tickets, including creating, updating, and retrieving tickets from the repository

    // Method to load tickets from a file
    public void loadFile(String pathToTicketsFile) throws InvalidTicketDataException {
        TicketFileLoader.load(pathToTicketsFile);
    }


}
