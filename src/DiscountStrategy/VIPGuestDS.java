package DiscountStrategy;

public class VIPGuestDS implements IDS {
    public double doDiscountPrice(double price) {
        return price * 0.2;
    }
}
