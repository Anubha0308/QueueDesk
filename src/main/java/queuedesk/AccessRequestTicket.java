package queuedesk;

public class AccessRequestTicket extends Ticket implements  Escalatable{
    String systemName;
    AccessLevel accessLevel;

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
        return;
    }

    @Override
    public boolean isEscalated() {
        return false;
    }

    @Override
    public int escalationLevel() {
        return 0;
    }

}
