package queuedesk.TicketManager;
import queuedesk.expectionPackage.InvalidTicketDataException;

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
                        //below two is the checking
                        Priority.valueOf(parts[3].toUpperCase());//this will throw Exception if not matches with already defined ones
                        Severity.valueOf(parts[5].toUpperCase());

                        tickets.add(new BugTicket(
                                parts[1],//title
                                parts[2],//description
                                parts[3].toUpperCase(),
                                parts[4],//requester
                                parts[5].toUpperCase(),
                                parts[6]));
                    } catch (IllegalArgumentException e) {
                        throw new InvalidTicketDataException(
                                "Invalid BUG ticket data on line " + lineNumber + ": " + e.getMessage());
                    }
                }
                else if(type.equals("ACCESS_REQUEST")){
                    try {
                        //below two is the checking
                        Priority.valueOf(parts[3].toUpperCase());//this will throw Exception if not matches with already defined ones
                        AccessLevel.valueOf(parts[5].toUpperCase());

                        tickets.add(new AccessRequestTicket(
                                parts[1],//title
                                parts[2],//description
                                parts[3].toUpperCase(),
                                parts[4],//requester
                                parts[5].toUpperCase(),
                                parts[6]));
                    } catch (IllegalArgumentException e) {
                        throw new InvalidTicketDataException(
                                "Invalid ACCESS_REQUEST ticket data on line " + lineNumber + ": " + e.getMessage());
                    }
                }
                else if(type.equals("FEATURE_REQUEST")){
                    try {
                        //below two is the checking
                        Priority.valueOf(parts[3].toUpperCase());//this will throw Exception if not matches with already defined ones
                        Integer.parseInt(parts[5]);

                        tickets.add(new FeatureRequestTicket(
                                parts[1],//title
                                parts[2],//description
                                parts[3].toUpperCase(),
                                parts[4],//requester
                                Integer.parseInt(parts[5]),
                                parts[6]));
                    } catch (NumberFormatException e) {
                        throw new InvalidTicketDataException(
                                "Invalid FEATURE_REQUEST ticket data on line " + lineNumber + ": " + e.getMessage());
                    }
                    catch (IllegalArgumentException e) {
                        throw new InvalidTicketDataException(
                                "Invalid FEATURE_REQUEST ticket data on line " + lineNumber + ": " + e.getMessage());
                    }
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
