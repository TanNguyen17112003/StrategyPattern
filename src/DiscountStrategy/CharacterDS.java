package DiscountStrategy;

public class CharacterDS implements IDS{
    public double doDiscountPrice(double price)
    {
        System.out.println("You are in discount 25%.");
        System.out.println("Your ticket is now cost: " + price*0.75);
        System.out.println("");
        return price*0.75;
    }
}
