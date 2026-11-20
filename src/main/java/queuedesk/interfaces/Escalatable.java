package queuedesk.interfaces;

public interface Escalatable {
    //in interface methods are public and abstract by default
    public void escalate();
    public boolean isEscalated();
    public int escalationLevel();
    default String escalationBadge(){
        return "Escalation Level";
    }
}
