package model;

public class Pizza extends MenuItem{
    private String crust;

    public Pizza(String size, String itemName, String crust) {
        super(size, itemName);
        this.crust = crust;
    }

    @Override
    public double getTotalPrice() {

        return switch (this.size) {
            case "8", "Personal" -> 8.5;
            case "12", "Medium" -> 12.0;
            case "16", "Large" -> 16.5;
            default -> throw new IllegalStateException("Unknown pizza size: " + size);
        };
    }
}
