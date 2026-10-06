package queuedesk.TicketManager;

import queuedesk.Escalatable;

public class AccessRequestTicket extends Ticket implements Escalatable {
    private String systemName;
    private AccessLevel accessLevel;
    private boolean escalated;
    private int escalationLevel;

    public AccessRequestTicket(String title,String description,String p,String requester,String systemName,String a){
        super(title,description,p,requester);
        this.systemName=systemName;
        this.accessLevel=AccessLevel.valueOf(a);
    }

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

        this.escalated=true;
        return this.escalated;
    }

    @Override
    public int escalationLevel() {
        return escalationLevel;
    }

    public AccessLevel getAccessLevel() {
        return this.accessLevel;
    }
    public String getSystemName(){
        return this.systemName;
    }
}
