package queuedesk;

import java.util.Comparator;

public class TicketComparator {//understand these comparator and how they relate to Comparator interface
   //like jaise hum implement karte hai Comparator Interface
   //but how to use these comparators in code
   //this ticketComparatorFirst is the object of the Comparator interface
   Comparator<Ticket> ticketComparatorFirst = Comparator.comparing(Ticket:: getPriorityOrdinal).reversed()
           .thenComparing(Ticket:: getCreatedAt);

   Comparator<Ticket> ticketComparator =
           Comparator.comparing(Ticket::getStatus)
                   .thenComparing(
                           Comparator.comparingLong(Ticket::ageInHours).reversed()
                   );
}
