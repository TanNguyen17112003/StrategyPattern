package DiscountStrategy;

public class CharacterDS implements IDS {
    public double doDiscountPrice(double price) {
        return price * 0.25;
    }
}
