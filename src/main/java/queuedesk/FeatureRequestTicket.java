package queuedesk;

public class FeatureRequestTicket extends Ticket{
    int votes;
    String businessJustification;

    public FeatureRequestTicket(String title,String description,String p,String requester,int votes,String businessJustification){
        super(title,description,p,requester);
        this.votes = votes;
        this.businessJustification = businessJustification;
    }

    @Override
    public double estimateEffortHours() {
        return 0;
    }
}
