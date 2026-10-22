package queuedesk.configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import queuedesk.TicketManager.Ticket;
import queuedesk.TicketManager.jsonhandler.TicketMixin;

public class JacksonConfig {
    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.addMixIn(Ticket.class, TicketMixin.class);

        return mapper;
    }
}
