package model;

import java.util.List;

public class ToppingCategory {
    private String category;
    private List<String> categoryOptions;

    public ToppingCategory(String category, List<String> categoryOptions) {
        this.category = category;
        this.categoryOptions = categoryOptions;
    }

    public List<String> getCategoryOptions() {
        return categoryOptions;
    }

    public String getCategory() {
        return category;
    }

    public double calculatePrice(String pizzaSize) {
        double price = 0;

            if (this.category.equalsIgnoreCase("meat")) {
                price = switch (pizzaSize) {
                    case "8", "Personal" -> 1.0;
                    case "12", "Medium" -> 2.0;
                    case "16", "Large" -> 3.0;
                    default -> throw new IllegalStateException("Unknown pizza size");
                };
            }
            else if (this.category.equalsIgnoreCase("cheese")){
                price = switch(pizzaSize){
                    case "8", "Personal" -> 0.75;
                    case "12", "Medium" -> 1.5;
                    case "16", "Large" -> 2.25;
                    default -> throw new IllegalStateException("Unknown pizza size");
                };

            } else if (this.category.equalsIgnoreCase("extra meat")) {
                price = switch (pizzaSize) {
                    case "8", "Personal" -> 0.5;
                    case "12", "Medium" -> 1.0;
                    case "16", "Large" -> 1.5;
                    default -> throw new IllegalStateException("Unknown pizza size");
                };
            }
            else if(this.category.equalsIgnoreCase("extra cheese")){
                price = switch(pizzaSize){
                    case "8", "Personal" -> 0.3;
                    case "12", "Medium" -> 0.6;
                    case "16", "Large" -> 0.9;
                    default -> throw new IllegalStateException("Unknown pizza size");
                };
            }

        return price;
    }
}
