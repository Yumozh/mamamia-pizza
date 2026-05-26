package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pizza extends MenuItem{
    private String crust;
    private static Map<String, Topping> toppingsMap;
    private Map<String, List<String>> selectedOptions;

    public Pizza(String size, String crust) {
        super(size, "regular");
        this.crust = crust;
        HashMap<String, Topping> toppingsMap = new HashMap<>();
        toppingsMap.put("meat", new Topping("meat", new ArrayList<>(List.of(
                "pepperoni", "sausage", "ham", "bacon", "chicken", "meatball"))));
        toppingsMap.put("cheese", new Topping("cheese", new ArrayList<>(List.of(
                "Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"))));

        toppingsMap.put("extra meat", new Topping("extra meat", new ArrayList<>(List.of(
                "pepperoni", "sausage", "ham", "bacon", "chicken", "meatball"))));
        toppingsMap.put("extra cheese", new Topping("extra cheese", new ArrayList<>(List.of(
                "Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"))));

        toppingsMap.put("regular", new Topping("regular", new ArrayList<>(List.of(
                "onions", "mushrooms", "bell peppers", "olives",
                "tomatoes", "spinach", "basil", "pineapple", "anchovies"))));
        toppingsMap.put("sauces", new Topping("sauces", new ArrayList<>(List.of(
                "marinara", "alfredo", "pesto", "bbq", "buffalo", "olive oil"))));
        toppingsMap.put("sides", new Topping("sides", new ArrayList<>(List.of(
                "red pepper", "parmesan"))));

        this.toppingsMap = toppingsMap;

        this.selectedOptions = new HashMap<>();
        this.selectedOptions.put("meat", new ArrayList<>());
        this.selectedOptions.put("cheese", new ArrayList<>());
        this.selectedOptions.put("extra meat", new ArrayList<>());
        this.selectedOptions.put("extra cheese", new ArrayList<>());
        this.selectedOptions.put("regular", new ArrayList<>());
        this.selectedOptions.put("sauces", new ArrayList<>());
        this.selectedOptions.put("sides", new ArrayList<>());

    }

    public Map<String, Topping> getToppingsMap() {
        return toppingsMap;
    }


    public void setToppingsMap(Map<String, Topping> toppingsMap) {
        this.toppingsMap = toppingsMap;
    }

    public double getBasePrice() {
        return switch (this.size) {
            case "8", "Personal" -> 8.5;
            case "12", "Medium" -> 12.0;
            case "16", "Large" -> 16.5;
            default -> throw new IllegalStateException("Unknown pizza size: " + size);
        };
    }

    public void addTopping(String category, String toppingName) {
        if (selectedOptions.containsKey(category)) {
            selectedOptions.get(category).add(toppingName);
        }else {
            System.out.println("Error: Category '" + category + "' not found!");
        }
    }

    public double calculateToppingTotal(){
        double toppingsTotal = 0;

        for (String category : selectedOptions.keySet()) {
            List<String> selectedInCategory = selectedOptions.get(category);
            int count = selectedInCategory.size();

            if (count > 0 ){
                Topping toppingCategory = toppingsMap.get(category);
                double pricePerItem = toppingCategory.calculatePrice(this.size);
                toppingsTotal += count * pricePerItem;
            }
        }
        return toppingsTotal;
    }

    @Override
    public double calculatePrice(String size) {
        return getBasePrice() + calculateToppingTotal();
    }
}
