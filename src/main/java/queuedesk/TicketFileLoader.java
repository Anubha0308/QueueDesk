package queuedesk;
import java.io.FileReader;
import java.io.IOException;
import  java.io.BufferedReader;
import  java.util.ArrayList;
import java.util.List;


public class TicketFileLoader {
    public static List<Ticket> load(String path){
        List<Ticket> tickets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber=1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 5) {
                    throw new InvalidTicketDataException("Invalid ticket data: " +line + "on line "+ lineNumber);
                }
                String type=parts[0].trim();
                if(type.equals("BUG")) {
                    //check if the data types of fields provided are compatible

                    for (int i = 1; i < parts.length; i++) {
                        String fieldValue = parts[i];
                        //see the tickets.txt file is text file means it only contains character i.e everything is string
                        //so we need to check if datatype compatible

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
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
