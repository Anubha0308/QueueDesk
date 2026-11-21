package queuedesk.TicketManager.jsonhandler;

import org.junit.jupiter.api.Test;
import queuedesk.TicketManager.BugTicket;
import queuedesk.TicketManager.Ticket;
import queuedesk.expectionPackage.InvalidTicketDataException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class TicketJsonStoreTest {

    @Test
    void testJson() throws InvalidTicketDataException {
        TicketJsonStore ticketJsonStore = new TicketJsonStore();
        //right now test on sample data
        //we want the sample data and the filePath
        List<Ticket> list=new ArrayList<>();
        list.add(new BugTicket("abc","abc","LOW","anubha","LOW","start the server"));
        Path path = Path.of("src/test/resources/tickets.json");
        ticketJsonStore.save(list,path);

        //how to check the result matches the assertion
    }
}
