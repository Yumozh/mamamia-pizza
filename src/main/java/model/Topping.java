package model;

public class Topping extends MenuItem{
    private boolean isRegular;

    public Topping(String size, String itemName, boolean isRegular) {
        super(size, itemName);
        this.isRegular = isRegular;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }
}
