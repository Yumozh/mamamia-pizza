package model;

public class GarlicKnots extends MenuItem{

    public GarlicKnots(String size, String itemName) {
        super(size, itemName);
    }

    @Override
    public double calculatePrice(String Size) {
        return 1.5;
    }
}
