package queuedesk.TicketManager;
import queuedesk.Escalatable;

import java.util.List;

public class BugTicket extends Ticket implements Escalatable {

    private Severity severity;
    private String stepsToReproduce;
    private boolean escalated;
    private int escalationLevel;

    public BugTicket(String title,String description,String p,String requester,String s,String stepsToReproduce){
        super(title,description,p,requester);
        this.stepsToReproduce=stepsToReproduce;
        this.severity=Severity.valueOf(s);
    }

    public double estimateEffortHours() {
        return 0;
    }


    @Override
    public void escalate(){
        this.escalated=true;
        this.escalationLevel=0;
        return;
    }

    @Override
    public boolean isEscalated() {
        return this.escalated;
    }

    @Override
    public int escalationLevel(){
        return this.escalationLevel;
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    void printTriageBoard(List<Ticket> tickets){
        for(Ticket ticket:tickets){
            this.estimateEffortHours();
            if(ticket instanceof BugTicket){
                BugTicket bugTicket=(BugTicket) ticket;
                System.out.println(bugTicket.escalationLevel());
            }
        }
    }
}
