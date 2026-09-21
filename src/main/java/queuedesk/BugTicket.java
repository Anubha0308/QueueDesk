package queuedesk;
import java.util.List;

public class BugTicket extends Ticket implements  Escalatable{

    private Severity severity;
    private String stepsToReproduce;

    BugTicket(String s,String stepsToReproduce){
        this.stepsToReproduce=stepsToReproduce;
        this.severity=Severity.valueOf(s);
    }

    public double estimateEffortHours() {
        return 0;
    }


    @Override
    public void escalate(){
        return;
    }

    @Override
    public boolean isEscalated() {
        return false;
    }

    @Override
    public int escalationLevel(){
        return 0;
    }

    void printTriageBoard(List<Ticket> tickets){
        for(Ticket ticket:tickets){
            this.estimateEffortHours();
            if(ticket instanceof BugTicket){
                System.out.println(this.escalationLevel());
            }
        }
    }
}
