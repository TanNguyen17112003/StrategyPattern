import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Ticket ticket = new Ticket("movie",150000);
        Scanner sc=new Scanner(System.in);
        ticket.printTicket();
        ticket.listPrice();
        int a=sc.nextInt();
        ticket.chooseIDS(a);
        System.out.println("You will pay the amount of "+ ticket.getPrice());
        ticket.listPay();
        a=sc.nextInt();
        ticket.chooseIPS(a);
        ticket.completePay();
        sc.close();
    }
}
