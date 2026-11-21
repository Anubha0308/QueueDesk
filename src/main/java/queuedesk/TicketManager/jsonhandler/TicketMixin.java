package queuedesk.TicketManager.jsonhandler;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import queuedesk.TicketManager.AccessRequestTicket;
import queuedesk.TicketManager.BugTicket;
import queuedesk.TicketManager.FeatureRequestTicket;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BugTicket.class, name = "bugticket"),
        @JsonSubTypes.Type(value = FeatureRequestTicket.class, name = "featurerequestticket"),
        @JsonSubTypes.Type(value = AccessRequestTicket.class, name = "accessrequestticket")
})

//this is for handling the polymorphic serialization and deserialization of Ticket objects in JSON format.
//It allows Jackson to correctly identify and instantiate the appropriate subclass of Ticket based on the "type" property in the JSON data.
public abstract class TicketMixin {

}
