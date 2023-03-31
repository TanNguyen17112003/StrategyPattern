package DiscountStrategy;

public class PreorderDS implements IDS{
    public double doDiscountPrice(double price)
    {
        System.out.println("You are in discount 10%.");
        System.out.println("Your ticket is now cost: " + price*0.9);
        System.out.println("");
        return price*0.9;
    }
}
