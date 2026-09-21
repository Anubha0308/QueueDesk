package queuedesk;

public interface Escalatable {
    //in interface methods are public and abstract by default
    void escalate();
    boolean isEscalated();
    int escalationLevel();
    default String escalationBadge(){
        return "Escalation Level";
    }
}
