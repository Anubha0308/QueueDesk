package queuedesk.TicketManager;
import lombok.*;
import lombok.AccessLevel;
import lombok.experimental.SuperBuilder;
import queuedesk.interfaces.Escalatable;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class BugTicket extends Ticket implements Escalatable {

    @Builder.Default
    private Severity severity = Severity.LOW;
    private String stepsToReproduce = null;

    @Setter(AccessLevel.NONE)
    private boolean escalated;
    @Setter(AccessLevel.NONE)
    private int escalationLevel;

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

    public String getStepsToReproduce() {
        if(this.stepsToReproduce==null || this.stepsToReproduce.isBlank()){
            return "No steps provided";
        }
        else{
            return this.stepsToReproduce;
        }
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
