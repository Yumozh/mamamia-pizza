package model;

public class Drink extends MenuItem{
    private String flavor;

    public Drink(String size, String itemName) {
        super(size, itemName);
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double calculatePrice(String size) {
        return switch (this.size) {
            case "small" -> 2.0;
            case "medium" -> 2.5;
            case "large" -> 3.0;
            default -> throw new IllegalStateException("Unknown drink size: " + size);
        };
    }
}
