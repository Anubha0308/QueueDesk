package queuedesk;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public abstract class Ticket implements Comparable<Ticket>{

    private static int nextId;
    private int id;
    private String description;
    private String title;
    private int requester;
    private Status status;
    private Priority priority;
    private LocalDateTime createdAt;
    private Optional<String> assignee=null;
    private Set<String> tags;

    {
        nextId=0;
    }
    Ticket(String title,String description,String p,int requester){
        nextId+=1;
        this.id=nextId;
        this.title=title;
        this.description=description;
        this.priority=Priority.valueOf(p.trim().toUpperCase());;
        this.requester=requester;
        this.createdAt=LocalDateTime.now();
    }
    Ticket(){

    }
    Ticket(String title,int requester){
        this(title,"Description","LOW",requester);
    }

    public abstract double estimateEffortHours();

    public int getID(){
        return this.id;
    }
    public String getDescription(){
        return this.description;
    }
    public String getTitle(){
        return this.title;
    }
    public int getRequester(){
        return this.requester;
    }
    public String getStatus(){
        return this.status.name();
    }
    public String getPriority(){
        return this.priority.name();
    }
    public String getAssignee() {
        return this.assignee.orElse("Unassigned");
    }
    public Set<String> getTags() {
        return this.tags;
    }
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setDescription(String description){
        this.description=description;
    }
    public void setPriority(String priority){
        this.priority=Priority.valueOf(priority.trim().toUpperCase());
    }
    public void setStatus(String status){
        this.status=Status.valueOf(status.trim().toUpperCase());
    }
    public void setAssignee(String assignee) {
        this.assignee=Optional.ofNullable(assignee);
    }
    public void setTags(Set<String> tags) {
        this.tags=Set.of(tags.toArray(new String[0]));
    }

    long ageInHours(){
        LocalDateTime currentTime= LocalDateTime.now();
        Duration duration= Duration.between(this.createdAt,currentTime);
        long hours=duration.toHours();
        return hours;
    }

    static int totalCreated(){
        return nextId;
    }

    @Override
    public int compareTo(Ticket o) {
        if(this.priority.ordinal()!=o.priority.ordinal()) {
            return Integer.compare(o.priority.ordinal(), this.priority.ordinal());
        }
        return this.createdAt.compareTo(o.createdAt);//return >0 <0 and 0 if same no breaking of code
    }

}
