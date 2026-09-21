package queuedesk;

public class AccessRequestTicket extends Ticket implements  Escalatable{
    String systemName;
    int accessLevel;

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
