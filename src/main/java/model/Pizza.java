package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pizza extends MenuItem{
    private String crust;
    private static Map<String, Topping> toppingsMap;

    public Pizza(String size, String itemName, String crust) {
        super(size, "regular");
        this.crust = crust;
        HashMap<String, Topping> toppingMap = new HashMap<>();
        toppingMap.put("meat", new Topping("meat", true, new ArrayList<>(List.of(
                "pepperoni", "sausage", "ham", "bacon", "chicken", "meatball"))));
        toppingMap.put("cheese", new Topping("cheese", true, new ArrayList<>(List.of(
                "Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo"))));
        toppingMap.put("regular", new Topping("regular", false, new ArrayList<>(List.of(
                "onions", "mushrooms", "bell peppers", "olives",
                "tomatoes", "spinach", "basil", "pineapple", "anchovies"))));
        toppingMap.put("sauces", new Topping("sauces", false, new ArrayList<>(List.of(
                "marinara", "alfredo", "pesto", "bbq", "buffalo", "olive oil"))));
        toppingMap.put("sides", new Topping("sides", false, new ArrayList<>(List.of(
                "red pepper", "parmesan"))));
        this.toppingsMap = toppingMap;
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


    public double calculateToppingTotal(){
        double toppingsTotal = 0;

        for (Topping toppingList : toppingsMap.values()) {
                toppingsTotal += toppingList.calculatePrice(size);
        }
        return toppingsTotal;
    }

    @Override
    public double calculatePrice(String size) {
        return getBasePrice() + calculateToppingTotal();
    }
}
