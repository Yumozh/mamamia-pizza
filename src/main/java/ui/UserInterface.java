package ui;

import model.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    Scanner scan = new Scanner(System.in);

    public void display() {
        System.out.println("===============================================================================");
        System.out.println("               🍕 WELCOME TO THE PIZZA MAMA MIA APP! 🍕");
        System.out.println("===============================================================================\n");
    }

    public void runHomeScreen() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Home Screen");
            System.out.println("""
                    \n
                    1. Make Order
                    x. Exit
                    """);
            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "Make Order", "Order" -> makeOrder();
                case "X", "Exit" -> isRunning = false;
                default -> throw new IllegalStateException("Invalid input! Try again.");
            }
        }
    }

    public void makeOrder() {
        boolean inOrderScreen = true;

        System.out.println("Enter your name: ");
        String customerName = scan.nextLine();

        Order order = new Order(customerName);

        while (inOrderScreen) {
            System.out.println("===============================================================================");
            System.out.println("                     🛒 PLACE YOUR PIZZA ORDER 🛒");
            System.out.println("===============================================================================\n");

            System.out.println("""
                    \n 
                    1. Add Pizza
                    2. Add Drink
                    3. Add Garlic Knots
                    4. Check Out
                    X. Home
                    """);
            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "add pizza" -> addPizzaToOrder(order);
                case "2", "add drink" -> addDrinkToOrder(order);
                case "3", "add garlic knots" -> addGarlicKnotsToOrder(order);
                case "4", "check out" -> checkout();
                default -> throw new IllegalStateException("Invalid input! Try again.");
            }
        }

    }

    public void addPizzaToOrder(Order order) {
        System.out.println("""
                        \n
                        Select size of your pizza from following:
                        1 - Personal (8")
                        2 - Medium (12")
                        3 - Large (16")
                """);
        String sizePizza = scan.nextLine();
        System.out.println("""
                        \n
                        Select crust of your pizza from following:
                        1 - Thin
                        2 - Regular
                        3 - Thick
                        4 - Cauliflower
                """);
        String crust = scan.nextLine();

        Pizza pizza = new Pizza(sizePizza, crust);
        displayCategoryMenu(pizza);
        order.addMenuItem(pizza);
    }

    public void displayCategoryMenu(Pizza pizza) {
        boolean addingToppings = true;
        boolean isExtraMeet = false;
        boolean isExtraCheese = false;

        while (addingToppings) {
            System.out.println("\\n=== SELECT TOPPING CATEGORY ===");
            System.out.println("""
                \n
                1. %s
                2. %s
                3. Add regular toppings
                4. Add sauces
                5. Add sides
                0. Done adding toppings
                """.formatted(isExtraMeet ? "Add Extra Meet" : "Add Meat", isExtraCheese ? "Add Extra Cheese" : "Add Cheese"));

            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "add meat", "add extra meat" -> {
                    if (isExtraMeet) {
                        displayToppingsOptions(pizza.getToppingsMap(), "extra meat", pizza);
                    } else {
                        isExtraMeet = displayToppingsOptions(pizza.getToppingsMap(), "meat", pizza);
                    }
                }
                case "2", "add cheese", "add extra cheese" -> {
                    if (isExtraCheese) {
                        displayToppingsOptions(pizza.getToppingsMap(), "extra cheese", pizza);
                    } else {
                        isExtraCheese = displayToppingsOptions(pizza.getToppingsMap(), "cheese", pizza);
                    }
                }
                case "3", "add regular toppings" -> displayToppingsOptions(pizza.getToppingsMap(), "regular", pizza);
                case "4", "add sauces" -> displayToppingsOptions(pizza.getToppingsMap(), "sauces", pizza);
                case "5", "add sides" -> displayToppingsOptions(pizza.getToppingsMap(), "sides", pizza);
                case "x", "exit" -> {
                    addingToppings = false;
                }
                default -> throw new IllegalStateException("Invalid input! Try again.");
            }
        }
    }

    public boolean displayToppingsOptions(Map<String, Topping> toppingsMap, String category, Pizza pizza){
        //display all meats available
        Topping topping = toppingsMap.get(category.toLowerCase());

        if (topping != null) {
            System.out.println("Available items in [" + category + "]:");
            int index = 0;
            for (String option : topping.getCategoryOptions()) {
                System.out.println(index + " " + option);
                index ++;
            }
        } else {
            System.out.println("Category '" + category + "' does not exist.");
            return false;
        }

        return addToppingToPizza(category, pizza, topping);
    }

    private boolean addToppingToPizza(String category, Pizza pizza, Topping topping) {
        System.out.println("Enter B to go back to topping categories.");
        //Enter meat name or exit
        String choice = scan.nextLine();

        int totalOptions = topping.getCategoryOptions().size();

        if (choice.equalsIgnoreCase("B")) {
            return false;
        }

        try {
            // Try parsing the input to an integer choice
            int choiceNumber = Integer.parseInt(choice);
            List<String> options = topping.getCategoryOptions();

            if (choiceNumber >= 0 && choiceNumber < totalOptions) {

                String selectedOptionName = options.get(choiceNumber);
                pizza.addTopping(category, selectedOptionName);

                System.out.println(selectedOptionName + " successfully added to your pizza!");

                return true;
            } else {
                System.out.println("Invalid selection number. Please try again.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'B' to go back.");
            return false;
        }
    }

    private void addDrinkToOrder(Order order){
        System.out.println("""
                        \n
                        Select size of your drink from following:
                        1 - Small
                        2 - Medium
                        3 - Large
                """);
        String drinkSize = scan.nextLine();
        System.out.println("""
                        \n
                        Select flavor of your drink from following:
                        1 - Cola
                        2 - Orange
                        3 - Lemon
                        4 - Root Beer
                """);
        String drinkFlavor = scan.nextLine();

        Drink drink = new Drink(drinkSize, drinkFlavor);
        order.addMenuItem(drink);
    }

    private void addGarlicKnotsToOrder(Order order){
        GarlicKnots garlicKnots = new GarlicKnots("regular", "garlic knots");
        order.addMenuItem(garlicKnots);
    }

    public void checkout(){

    }


}