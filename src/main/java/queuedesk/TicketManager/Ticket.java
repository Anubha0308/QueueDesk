package queuedesk.TicketManager;

import lombok.*;
import lombok.AccessLevel;
import lombok.experimental.SuperBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Ticket implements Comparable<Ticket>{

    private static int nextId=0;

    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private int id = nextId++;

    private String title;
    private String description;
    private String requester;

    @Builder.Default
    private Status status=Status.OPEN;//sabko ye value mil jayegi
    //when the status of ticket is changed

    @Builder.Default
    private Priority priority=Priority.LOW;

    @Setter(AccessLevel.NONE)
    @Builder.Default
    private LocalDateTime createdAt=LocalDateTime.now();

    //always that the time of ticket creation we do not know the assignee
    private String assignee = null;//logic wise intially assignee null hi hoga na jab ticket create hoga ig
    private Set<String> tags = Set.of();//intially empty set of tags

    public abstract double estimateEffortHours();

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getAssignee() {
        return (this.assignee == null || this.assignee.isBlank()) ? "Unassigned" : this.assignee;
    }

    public void setTags(Set<String> tags) {
        this.tags=Set.of(tags.toArray(new String[0]));
    }

    public int getPriorityOrdinal() {
        return this.priority.ordinal();
    }

    public int getStatusOrdinal() {
        return this.status.ordinal();
    }
    public long ageInHours(){
        LocalDateTime currentTime= LocalDateTime.now();
        Duration duration= Duration.between(this.createdAt,currentTime);
        long hours=duration.toHours();
        return hours;
    }

    static int totalCreated(){
        return nextId;
    }

    @Override
    public int compareTo(Ticket o) {//NATURAL ORDERING OF TICKET OBJECTS-this is automatically used by PrioirtyQueue when no explicit is given
        if (this.priority.ordinal() != o.priority.ordinal()) {
            return Integer.compare(o.priority.ordinal(), this.priority.ordinal());
        }
        return this.createdAt.compareTo(o.createdAt);//return >0 <0 and 0 if same no breaking of code
    } //Bug ticket , feature ticket and access request ticket can use this and can also override


}
