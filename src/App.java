import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            Ticket ticket = new Ticket("movie",150000);
            ticket.printTicket();
            ticket.listPrice();
            int a=sc.nextInt();
            ticket.chooseIDS(a);
            System.out.println("You will pay the amount of "+ ticket.getPrice());
            ticket.listPay();
            a=sc.nextInt();
            ticket.chooseIPS(a);
            ticket.completePay();
            System.out.println("");
            System.out.println("Press:");
            System.out.println("0: To exit.");
            System.out.println("Others: To continue.");
            sc.nextLine(); // Lấy ký tự trước đó nhập còn sót trong sc.
            if (sc.nextLine().equals("0")) break;
            System.out.println("--------------------------------");
        }
        sc.close();
    }
}
