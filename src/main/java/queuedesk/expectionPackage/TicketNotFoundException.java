package queuedesk.expectionPackage;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String message){
        super(message);
    }
}
