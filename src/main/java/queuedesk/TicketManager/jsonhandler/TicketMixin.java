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
public abstract class TicketMixin {

}
