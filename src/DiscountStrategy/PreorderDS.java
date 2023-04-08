package DiscountStrategy;

public class PreorderDS implements IDS {
    public double doDiscountPrice(double price) {
        return price * 0.1;
    }
}
