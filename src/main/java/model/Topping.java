package model;

import java.util.List;

public class Topping extends MenuItem {
    private boolean isRegular;
    private List<String> availableOptions;


    public Topping(String size, String itemName, boolean isRegular) {
        super(size, itemName);
        this.isRegular = isRegular;
    }

    @Override
    public double getTotalPrice() {
        if (isRegular) {
            return 0;
        } else {
            return switch (this.size) {
                case "8", "Personal" -> 1.0;
                case "12", "Medium" -> 12.0;
                case "16", "Large" -> 16.5;
                default -> throw new IllegalStateException("Unknown pizza size: " + size);
            };
        }
    }
}
