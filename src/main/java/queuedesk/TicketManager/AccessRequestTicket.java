package queuedesk.TicketManager;

import lombok.*;
import lombok.experimental.SuperBuilder;
import queuedesk.interfaces.Escalatable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class AccessRequestTicket extends Ticket implements Escalatable {

    private AccessLevel accessLevel;
    private String systemName;

    @Setter(lombok.AccessLevel.NONE)
    private boolean escalated;

    @Setter(lombok.AccessLevel.NONE)
    private int escalationLevel;



    @Override
    public double estimateEffortHours() {
        return 0;
    }

    @Override
    public void escalate() {
        this.escalated=true;
        this.escalationLevel=0;
        return;
    }

    @Override
    public boolean isEscalated() {
        return this.escalated;
    }

    @Override
    public int escalationLevel() {
        return escalationLevel;
    }

}
