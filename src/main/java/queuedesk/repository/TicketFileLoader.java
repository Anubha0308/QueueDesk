package queuedesk.repository;
import queuedesk.expectionPackage.InvalidTicketDataException;
import queuedesk.TicketManager.BugTicket;
import queuedesk.TicketManager.Priority;
import queuedesk.TicketManager.Severity;
import queuedesk.TicketManager.Ticket;

import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import  java.io.BufferedReader;
import  java.util.ArrayList;
import java.util.List;


public class TicketFileLoader {
    public static List<Ticket> load(String path) throws InvalidTicketDataException {
        List<Ticket> tickets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber=1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);//-1 keeps the trailing empty strings in parts array
                if (parts.length != 7) {
                    throw new InvalidTicketDataException(
                            "Invalid ticket data on line " + lineNumber + ": " + line);
                }
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }

                String type=parts[0];
                if(type.equals("BUG")) {
                    try {
                        Priority.valueOf(parts[3].toUpperCase());//this will throw Exception if not matches with already defined ones
                        Severity.valueOf(parts[5].toUpperCase());

                        tickets.add(new BugTicket(
                                parts[1],
                                parts[2],
                                parts[3].toUpperCase(),
                                parts[4],
                                parts[5].toUpperCase(),
                                parts[6]));
                    } catch (IllegalArgumentException e) {
                        throw new InvalidTicketDataException(
                                "Invalid BUG ticket data on line " + lineNumber + ": " + e.getMessage());
                    }
                }
                else if(type.equals("ACCESS_REQUEST")){

                }
                else if(type.equals("FEATURE_REQUEST")){

                }
                else{
                    throw new InvalidTicketDataException("Ticket object cannot be created for this type. "+type +"on line "+lineNumber);
                }
                lineNumber+=1;
            }
        }
        catch (IOException e) {
            throw new UncheckedIOException("Unable to read ticket file: " + path, e);
        }
        return tickets;
    }
}
