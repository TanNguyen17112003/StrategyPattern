import DiscountStrategy.*;
import PaymentStrategy.*;

public class Ticket {
    private double price;
    private double dsPrice;
    private double psPrice;
    private IDS discountStrategy;
    private IPS paymentStrategy;

    public Ticket(double price) {

        this.price = price;
        this.dsPrice = 0;
        this.psPrice = 0;
    }

    public Ticket() {
        this(0);
        this.dsPrice = 0;
        this.psPrice = 0;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public double getDSPrice() {
        return this.dsPrice;
    }

    public void setDSPrice(double discountPrice) {
        this.dsPrice = discountPrice;
    }

    public double getPSPrice() {
        return this.psPrice;
    }

    public void setPSPrice(double discountPrice) {
        this.psPrice = discountPrice;
    }

    public void setDiscountStrategy(IDS discountStrategy) {
        this.discountStrategy = discountStrategy;
        this.dsPrice = discountStrategy.doDiscountPrice(price);
    }

    public IDS getDiscountStrategy() {
        return this.discountStrategy;
    }

    public void setPaymentStrategy(IPS paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        this.psPrice = paymentStrategy.pay(price);
    }

    public IPS getPaymentStrategy() {
        return this.paymentStrategy;
    }

    public void printTicket() {
        System.out.println("Ticket");
        System.out.println("Price: " + this.price);
    }

    public void printDiscount() {
        System.out.println("Your price is: " + this.price);
        System.out.println("Discount for discount: " + (this.dsPrice));
        System.out.println("Discount for payment: " + (this.psPrice));
        System.out.println("Total price: " + (this.price - this.dsPrice - this.psPrice));
        System.out.println("\n");
        ;
    }

    public void chooseIDS(int a) {
        if (a == 1)
            this.setDiscountStrategy(new NoDS());
        else if (a == 2)
            this.setDiscountStrategy(new CharacterDS());
        else if (a == 3)
            this.setDiscountStrategy(new PreorderDS());
        else if (a == 4)
            this.setDiscountStrategy(new VIPGuestDS());
    }

    public void chooseIPS(int a) {
        if (a == 1)
            this.setPaymentStrategy(new CreditCardPS());
        else if (a == 2)
            this.setPaymentStrategy(new MOMOPS());
        else if (a == 3)
            this.setPaymentStrategy(new PaypalPS());
    }

    public void completePay() {
        paymentStrategy.pay(price);
    }
}