package queuedesk;

import queuedesk.TicketManager.Ticket;
import queuedesk.TicketManager.TicketFileLoader;
import queuedesk.ticketRepository.Repository;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {

    private static class simulatorResult{
        int served;
        int VIP;
        int priorityRecheck;
        simulatorResult(int served,int VIP,int priorityRecheck){
            this.served=served;
            this.VIP=VIP;
            this.priorityRecheck=priorityRecheck;
        }
        void printResult(){
            System.out.printf("Total number of customers served %s are , total VIP are %s, total priority-recheck are %s.\n", this.served, this.VIP, this.priorityRecheck);
        }
    }
    public static simulatorResult queueNumberSimulator(int initialTicketNumber,int numberOfCustomers){

        int served=0, priorityRecheck=0, VIP=0;
        for(int i=1;i<=numberOfCustomers;i++){
            System.out.printf("Now serving ticket %s\n",i);
            served+=1;
            if(i%5==0) VIP+=1;
            if(i%10==0) priorityRecheck+=1;
        }
        simulatorResult result= new simulatorResult(served,VIP,priorityRecheck);
        return result;
    }

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        int initialTicketNumber;
        int numberOfCustomers;
        System.out.println("Enter the initial ticket Number and the number of customers:\n");
        initialTicketNumber=sc.nextInt();
        numberOfCustomers=sc.nextInt();
        simulatorResult result = queueNumberSimulator(initialTicketNumber,numberOfCustomers);
        result.printResult();

        //once we have to create mapper


        //all the three classes extend the ticket class and
        //bugticket and accessRequestTicket implements escalatable also
        List<Ticket> tickets = List.of();//rn empty list
        String path="src/main/java/queuedesk/tickets.txt";
        try{

            tickets = TicketFileLoader.load(path);
            for(Ticket ticket:tickets){
                System.out.println(ticket.getID());
                System.out.println(ticket.getTitle());
                System.out.println(ticket.getDescription());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        //we may use this tickets list to create a repository and then to generate a report
        //repository is common for all three subclasses - rn considering this
        Repository repository = new Repository();
        for(Ticket ticket : tickets) {
            repository.add(ticket);//the list in repository is private
        }
        //repository is the store
        //how do we generate report , on what basis we generate report
        return;
    }
}