package model;

public class Drink extends MenuItem{
    private String flavor;

    public Drink(String size, String flavor) {
        super(size, "drink");
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice(){
        return switch (this.size) {
            case "small" -> 2.0;
            case "medium" -> 2.5;
            case "large" -> 3.0;
            default -> throw new IllegalStateException("Unknown drink size: " + size);
        };
    }
}
