package queuedesk.expectionPackage;

public class InvalidTicketDataException extends Exception{
    public InvalidTicketDataException(String message){
        super(message);
    }
}
