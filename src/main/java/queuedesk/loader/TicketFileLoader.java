package queuedesk.loader;
import queuedesk.TicketManager.*;
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
                //switch to switch
                switch (type) {
                    case "BUG":
                        try {
                            //below two is the checking
                            Priority.valueOf(parts[3].toUpperCase());//this will throw Exception if not matches with already defined ones
                            Severity.valueOf(parts[5].toUpperCase());

                            tickets.add(BugTicket.builder()
                                    .title(parts[1])
                                    .description(parts[2])
                                    .priority(Priority.valueOf(parts[3].toUpperCase()))
                                    .requester(parts[4])
                                    .severity(Severity.valueOf(parts[5].toUpperCase()))
                                    .stepsToReproduce(parts[6].isEmpty() ? null : parts[6])
                                    .build()
                            );
                        } catch (IllegalArgumentException e) {
                            throw new InvalidTicketDataException(
                                    "Invalid BUG ticket data on line " + lineNumber + ": " + e.getMessage());
                        }
                        break;
                    case "ACCESS_REQUEST":
                        try {
                            //below two is the checking
                            Priority.valueOf(parts[3].toUpperCase());//this will throw Exception if not matches with already defined ones
                            AccessLevel.valueOf(parts[5].toUpperCase());

                            tickets.add(AccessRequestTicket.builder()
                                    .title(parts[1])
                                    .description(parts[2])
                                    .priority(Priority.valueOf(parts[3].toUpperCase()))
                                    .requester(parts[4])
                                    .accessLevel(AccessLevel.valueOf(parts[5].toUpperCase()))
                                    .systemName(parts[6].isEmpty() ? null : parts[6])
                                    .build()
                            );


                        } catch (IllegalArgumentException e) {
                            throw new InvalidTicketDataException(
                                    "Invalid ACCESS_REQUEST ticket data on line " + lineNumber + ": " + e.getMessage());
                        }
                        break;
                    case "FEATURE_REQUEST":
                        try {
                            //below two is the checking
                            Priority.valueOf(parts[3].toUpperCase());//this will throw Exception if not matches with already defined ones
                            Integer.parseInt(parts[5]);

                            tickets.add(FeatureRequestTicket.builder()
                                    .title(parts[1])
                                    .description(parts[2])
                                    .priority(Priority.valueOf(parts[3].toUpperCase()))
                                    .requester(parts[4])
                                    .votes(Integer.parseInt(parts[5]))
                                    .businessJustification(parts[6].isEmpty() ? null : parts[6])
                                    .build()
                            );


                        } catch (IllegalArgumentException e) {
                            throw new InvalidTicketDataException(
                                    "Invalid FEATURE_REQUEST ticket data on line " + lineNumber + ": " + e.getMessage());
                        }
                        break;
                    default:
                        throw new InvalidTicketDataException("Invalid ticket type on line " + lineNumber + ": " + type);
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
