package queuedesk.TicketManager;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class FeatureRequestTicket extends Ticket {

    int votes;
    String businessJustification;


    @Override
    public double estimateEffortHours() {
        return 0;
    }

    public String getBusinessJustification() {
        if(this.businessJustification==null || this.businessJustification.isBlank()){
            return "No business justification provided";
        }
        else{
            return this.businessJustification;
        }
    }

}
