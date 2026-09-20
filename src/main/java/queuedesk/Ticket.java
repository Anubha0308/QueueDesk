package queuedesk;

import java.time.Duration;
import java.time.LocalDateTime;

public class Ticket {

    private static int nextId;
    private int id;
    private String description;
    private String title;
    private int requester;
    private Status status;
    private Priority priority;
    LocalDateTime createdAt;
    {
        nextId=0;
    }
    Ticket(String title,String description,String priority,int requester){
        nextId+=1;
        this.title=title;
        this.description=description;
        this.priority=Priority.valueOf(priority.trim().toUpperCase());;
        this.requester=requester;
        this.createdAt=LocalDateTime.now();
    }

    Ticket(String title,int requester){
        this(title,"Description","LOW",requester);
    }

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

    public void setDescription(String description){
        this.description=description;
    }
    public void setPriority(String priority){
        this.priority=Priority.valueOf(priority.trim().toUpperCase());
    }
    public void setStatus(String status){
        this.status=Status.valueOf(status.trim().toUpperCase());
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


}
