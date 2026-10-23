package queuedesk.TicketManager.jsonhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import queuedesk.TicketManager.Ticket;
import queuedesk.configuration.JacksonConfig;
import queuedesk.expectionPackage.InvalidTicketDataException;

import java.io.IOException;
import java.nio.file.Path;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TicketJsonStore {
    //this has the save and the load methods for the tickets
    //we save and load from a JSON file
    void save (List <Ticket> tickets, Path filePath) throws InvalidTicketDataException {
        File file = filePath.toFile();
        try {
            JacksonConfig.MAPPER.writeValue(file, tickets);
        }
        catch (IOException e){
            throw new InvalidTicketDataException("Error processing ticket data to JSON");
        }
    }

    List<Ticket> load(Path filePath) throws InvalidTicketDataException {
        File file = filePath.toFile();
        try {
            List<Ticket> tickets = new ArrayList<>();
            tickets = JacksonConfig.MAPPER.readValue(file, new TypeReference<List<Ticket>>(){});
            return tickets;
        }
        catch (IOException e) {
            throw new InvalidTicketDataException("Error processing ticket data from JSON");
        }
    }
}
