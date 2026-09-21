package queuedesk;

public class FeatureRequestTicket extends Ticket{
    int votes;
    String businessJustification;

    @Override
    public double estimateEffortHours() {
        return 0;
    }
}
