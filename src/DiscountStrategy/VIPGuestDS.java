package DiscountStrategy;

public class VIPGuestDS implements IDS{
    public double doDiscountPrice(double price)
    {
        System.out.println("You are in discount 20%.");
        System.out.println("Your ticket is now cost: " + price*0.8);
        System.out.println("");
        return price*0.8;
    }
}
