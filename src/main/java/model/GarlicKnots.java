package model;

public class GarlicKnots extends MenuItem{

    public GarlicKnots(String size, String itemName) {
        super(size, itemName);
    }

    @Override
    public double getPrice(){
        return 1.5;
    }
}
