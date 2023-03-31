package DiscountStrategy;

public class NoDS implements IDS{
    public double doDiscountPrice(double price){
        System.out.println("You are not in discount.");
        System.out.println("Your ticket price is still: " + price);
        System.out.println("");
        return price;
    }
}
