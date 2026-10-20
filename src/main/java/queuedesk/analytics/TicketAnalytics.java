package queuedesk.analytics;

import queuedesk.TicketManager.Priority;
import queuedesk.TicketManager.Status;
import queuedesk.TicketManager.Ticket;

import java.util.*;
import java.util.stream.Collectors;

public class TicketAnalytics {
    //will use the streams in this class to analyze the tickets and return the results

    public Map<Priority, Long> countByPriority(List<Ticket> tickets) {
        Map<Priority, Long> map = new HashMap<>();
//        for(Ticket t:tickets){
//            if(map.containsKey(t.getPriority())){
//                map.put(t.getPriority(), map.get(t.getPriority()) + 1);
//            } else {
//                map.put(t.getPriority(), 1L);
//            }
//        }
        map =tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getPriority, Collectors.counting()));

        return map;
    }

    public OptionalDouble averageEffortHours(List<Ticket> tickets) {
        if(tickets.isEmpty()) {
            return OptionalDouble.empty();
        }//logically returning 0.0 for empty list is not correct
        //OptionalDouble.of(tickets.stream().mapToDouble(Ticket::estimateEffortHours).average().orElse(0.0));
        return tickets.stream()
                .mapToDouble(Ticket:: estimateEffortHours).average();
    }

    public OptionalDouble totalEffortHours(List<Ticket> ticktets) {
        if(ticktets.isEmpty()) {
            return OptionalDouble.empty();
        }
        return OptionalDouble.of(ticktets.stream()
                .mapToDouble(Ticket::estimateEffortHours)
                .sum());
    }

    public Optional<Ticket> oldestTicket(List<Ticket> tickets) {
        if(tickets.isEmpty()) {
            return Optional.empty();
        }

        Optional<Ticket> oldestTicket = tickets.stream()
                .filter(t-> t.getStatus() == Status.OPEN)
                .min((t1,t2)-> t1.getCreatedAt().compareTo(t2.getCreatedAt()));
        //this min is the terminal operation here
        //if none of the tickets are open then it should return empty optional rather null
        return oldestTicket;
    }


}