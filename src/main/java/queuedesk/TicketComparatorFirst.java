package queuedesk;
import java.util.Comparator;

public class TicketComparatorFirst implements Comparator<Ticket>{
    @Override
    public int compare(Ticket o1, Ticket o2) {
        if(o1.getPriorityOrdinal() == o2.getPriorityOrdinal()){
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        }
        return Integer.compare(o2.getPriorityOrdinal(),o1.getPriorityOrdinal());
    }
}
//aur similarly jab hume TicketComparator ka use karna hoga to uska object bana kar use karenge
