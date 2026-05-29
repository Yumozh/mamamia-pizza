package model;

import java.util.*;

public class Pizza extends MenuItem {
    private String crust;
    private EnumMap<Category, List<String>> selectedOptions;

    public Pizza(String size, String crust) {
        super(size, "Regular");
        this.crust = crust;
        this.selectedOptions = new EnumMap<>(Category.class);

        for (Category key : ToppingCatalog.CATEGORIES.keySet()) {
            this.selectedOptions.put(key, new ArrayList<>());
        }
    }

    public Map<Category, List<String>> getSelectedOptions() {
        return selectedOptions;
    }

    public Map<Category, ToppingCategory> getToppingsMap() {
        return ToppingCatalog.CATEGORIES;
    }

    public String getCrust() {
        return crust;
    }

    public double getBasePrice() {
        return switch (this.size) {
            case "8", "Personal" -> 8.5;
            case "12", "Medium" -> 12.0;
            case "16", "Large" -> 16.5;
            default -> throw new IllegalStateException("Unknown pizza size: " + size);
        };
    }

    public void addTopping(Category category, String toppingName) {
        selectedOptions.get(category).add(toppingName);
    }

    public void removeTopping(Category category, String toppingName) {
        selectedOptions.get(category).remove(toppingName);
        System.out.println("ToppingCategory was removed from your order!");
    }

    public boolean isAtLeastOneToppingProvided(Category category)
    {
        if (selectedOptions.containsKey(category)) {
            return !selectedOptions.get(category).isEmpty();
        }

        return false;
    }

    public double calculateToppingTotal(){
        double toppingsTotal = 0;

        for (Category category : selectedOptions.keySet()) {
            List<String> selectedInCategory = selectedOptions.get(category);
            int count = selectedInCategory.size();

            if (count > 0 ){
                ToppingCategory toppingCategory = ToppingCatalog.CATEGORIES.get(category);
                double pricePerItem = toppingCategory.calculatePrice(this.size);
                toppingsTotal += count * pricePerItem;
            }
        }
        return toppingsTotal;
    }

    @Override
    public double getPrice() {
        return getBasePrice() + calculateToppingTotal();
    }
}
