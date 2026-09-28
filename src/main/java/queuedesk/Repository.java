package queuedesk;
import java.util.*;

public class Repository<T extends Ticket> {
    private List<T> list = new ArrayList<>();
    public void add(T item){
        list.add(item);
    }
    public List<T> getAll(){
        List<T> newList = new ArrayList<T>(list);
        return newList;
    }
    public T getById(int id){
        for(T item:list){
            if(item.getID()==id){
                return item;
            }
        }
        return null;
    }
    public Map<String, List<T>> groupByAssignee(){
        Map<String, List<T>> map = new HashMap<>();
        for(T item: list){
            String assignee = item.getAssignee();
            if(!map.containsKey(assignee)){
                map.put(assignee, new ArrayList<>());
            }
            map.get(assignee).add(item);
        }
        return map;
    }

    public static PriorityQueue<Ticket> naturalQueue = new PriorityQueue<>();//this internally uses compareTo implementation of Ticket
    public static PriorityQueue<Ticket> ticketPriorityQueue = new PriorityQueue<>(TicketComparator.ticketComparatorFirst);

    public static <T extends Ticket> T oldest(List<T> tickets){
        //may be jo sabse purana ticket ho usko return karna
        int size=tickets.size();
        if(size==0){
            return null;
        }
        T oldestTicket=tickets.getFirst();//get(0) is replaced with getFirst()
        for(T ticket: tickets){
            if(ticket.getCreatedAt().isBefore(oldestTicket.getCreatedAt())){
                oldestTicket=ticket;
            }
        }
        return oldestTicket;
    }
}
