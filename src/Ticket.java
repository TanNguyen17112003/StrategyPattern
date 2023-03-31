import DiscountStrategy.*;
import PaymentStrategy.*;

public class Ticket{
    private String name;
    private double price;
    private IDS discountStrategy;
    private IPS paymentStrategy;

    public Ticket(String name, double price){
        this.name=name;
        this.price=price;
    }
    public Ticket(){
        this("",0);
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public double getPrice(){
        return this.price;
    }
    public void setDiscountStrategy(IDS discountStrategy){
        this.discountStrategy=discountStrategy;
        price = discountStrategy.doDiscountPrice(price);
    }
    public IDS getDiscountStrategy(){
        return this.discountStrategy;
    }
    public void setPaymentStrategy(IPS paymentStrategy){
        this.paymentStrategy=paymentStrategy;
    }
    public IPS getPaymentStrategy(){
        return this.paymentStrategy;
    }
    public void printTicket(){
        System.out.println("Ticket");
        System.out.println("Name: " + this.name);
        System.out.println("Price: "+ this.price);
    }
    public void listPrice(){
        System.out.println("What category do you belong to?");
        System.out.println("1. NoDS: No discount promote");
        System.out.println("2. CharacterDS: 25% discount promote");
        System.out.println("3. PreorderDS: 10% discount promote");
        System.out.println("4. VIPGuestDS: 20% discount promote");
    }
    public void listPay(){
        System.out.println("How would you like to pay?");
        System.out.println("1. Creditcard: reduce 10% price");
        System.out.println("2. MOMO: have some enlows along with such as foods, water");
        System.out.println("3. Paypal: give back 5% of current price");
    }
    public void chooseIDS(int a){
        if(a==1) this.setDiscountStrategy(new NoDS());
        else if(a==2) this.setDiscountStrategy(new CharacterDS());
        else if(a==3) this.setDiscountStrategy(new PreorderDS());
        else if(a==4) this.setDiscountStrategy(new VIPGuestDS());
    }
    public void _Price(){
        
    }
    public void chooseIPS(int a){
        if(a==1) this.setPaymentStrategy(new CreditCardPS());
        else if(a==2) this.setPaymentStrategy(new MOMOPS());
        else if(a==3) this.setPaymentStrategy(new PaypalPS());
    }
    public void completePay(){
        paymentStrategy.pay(price);
    }
}