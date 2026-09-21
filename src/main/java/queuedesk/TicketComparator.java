package queuedesk;

import java.util.Comparator;

public class TicketComparator {

   Comparator<Ticket> ticketComparatorFirst = Comparator.comparing(Ticket:: getPriority).reversed()
           .thenComparing(Ticket:: getCreatedAt);

   Comparator<Ticket> ticketComparatorSecond = Comparator.comparing(Ticket:: getStatus)
           .thenComparing(Ticket:: ageInHours).reversed();
}
