package ui;
import model.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    Scanner scan = new Scanner(System.in);
    private Order currentOrder;

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

        currentOrder = new Order(customerName);

        while (inOrderScreen) {
            System.out.println("===============================================================================");
            System.out.println("                     🛒 PLACE YOUR PIZZA ORDER 🛒");
            System.out.println("===============================================================================\n");

            System.out.println("""
                    \n 
                    1. Add Pizza
                    2. Add Signature Pizza
                    3. Add Drink
                    4. Add Garlic Knots
                    5. Check Out
                    0. Cancel Order
                    """);
            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "add pizza" -> addPizzaToOrder(currentOrder);
                case "2", "add signature pizza" -> addSignaturePizza(currentOrder);
                case "3", "add drink" -> addDrinkToOrder(currentOrder);
                case "4", "add garlic knots" -> addGarlicKnotsToOrder(currentOrder);
                case "5", "check out" -> {
                    checkout(currentOrder);
                    inOrderScreen = false;
                }
                case "0", "cancel" -> {
                    System.out.println("Order cancelled. Returning to home screen...");
                    inOrderScreen = false;
                }
                default -> throw new IllegalStateException("Invalid input! Try again.");
            }
        }
    }

    public void addPizzaToOrder(Order order) {
        System.out.println("""
                        \n
                        Select size of your pizza from following:
                        Personal (8")
                        Medium (12")
                        Large (16")
                """);
        String sizePizza = scan.nextLine();
        System.out.println("""
                        \n
                        Select crust of your pizza from following:
                        - thin
                        - regular
                        - thick
                        - cauliflower
                """);
        String crust = scan.nextLine();

        Pizza pizza = new Pizza(sizePizza, crust);
        displayCategoryMenu(pizza);
        order.addMenuItem(pizza);
    }

    public void addSignaturePizza(Order order){
        boolean inSignaturePizza = true;
        MargheritaPizza pizzaMargherita = new MargheritaPizza();
        VeggiePizza pizzaWithVeggies = new VeggiePizza();

            while (inSignaturePizza){
            System.out.println("===============================================================================\n");
            System.out.println("                     SIGNATURE PIZZA");
            System.out.println("===============================================================================\n");
            System.out.println("""
                            \n
                            1. Margherita
                            2. Veggie Pizza
                            3. Home Screen
                    """);
            String choice = scan.nextLine();
            switch(choice){
                case "1" -> {
                    askForAction(pizzaMargherita);
                    order.addMenuItem(pizzaMargherita);
                    inSignaturePizza = false;
                }
                case "2" -> {
                    askForAction(pizzaWithVeggies);
                    order.addMenuItem(pizzaWithVeggies);
                    inSignaturePizza = false;
                }
                case "3" -> {
                    inSignaturePizza = false;
                }
            }
        }
    }

    public void askForAction(Pizza pizza) {
        while (true) {
            System.out.println("\n--- Modify Your Pizza ---");
            System.out.println("1. Add a topping");
            System.out.println("2. Remove a topping");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String input = scan.nextLine().trim().toLowerCase();

            switch (input) {
                case "1" -> displayCategoryMenu(pizza);
                case "2" -> startRemoveToppingsPrompt(pizza);
                case "3", "exit" -> {
                    System.out.println("Finished modifying pizza.");
                    return;
                }
                default -> System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }

    public void displayCategoryMenu(Pizza pizza) {
        boolean addingToppings = true;
        boolean isExtraMeet = pizza.isAtLeastOneToppingProvided("meat");
        boolean isExtraCheese = pizza.isAtLeastOneToppingProvided("cheese");

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
                case "0", "exit" -> {
                    addingToppings = false;
                }
                default -> throw new IllegalStateException("Invalid input! Try again.");
            }
        }
    }

    public boolean displayToppingsOptions(Map<String, Topping> toppingsMap, String category, Pizza pizza) {
        //display all meats available
        Topping topping = toppingsMap.get(category.toLowerCase());

        if (topping != null) {
            System.out.println("Available items in [" + category + "]:");
            int index = 0;
            for (String option : topping.getCategoryOptions()) {
                System.out.println(index + " " + option);
                index++;
            }
        } else {
            System.out.println("Category '" + category + "' does not exist.");
            return false;
        }

        return addToppingToPizza(category, pizza, topping);
    }

    public void startRemoveToppingsPrompt(Pizza pizza)
    {
        while (true) {
            Map<String, List<String>> toppings = pizza.getSelectedOptions();

            for (Map.Entry<String, List<String>> entry : toppings.entrySet()) {
                if (entry.getValue().isEmpty()) {
                    continue;
                }

                System.out.println(entry.getKey());

                for (String topping : entry.getValue()) {
                    System.out.println("    " + topping);
                }
            }

            System.out.print("\nEnter a topping to remove (or type 'exit' to finish): ");
            String input = scan.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting topping removal.");
                break; // Breaks out of the while loop immediately
            }

            String targetCategory = null;
            String exactToppingName = null;

            for (Map.Entry<String, List<String>> entry : toppings.entrySet()) {
                for (String topping : entry.getValue()) {
                    if (topping.equalsIgnoreCase(input)) {
                        targetCategory = entry.getKey();
                        exactToppingName = topping; // Capture exact case for the remove method
                        break;
                    }
                }
                if (targetCategory != null) {
                    break;
                }
            }

            if (targetCategory == null) {
                System.out.println("Error: '" + input + "' is not currently on your pizza. Please try again.\n");
                continue;
            }

            pizza.removeTopping(targetCategory, exactToppingName);
            System.out.println();
        }
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
        System.out.println("===============================================================================");
        System.out.println("                     DRINKS LIST ");
        System.out.println("===============================================================================\n");
        System.out.println("""
                        \n
                        Select size of your drink from following:
                        small
                        medium
                        large
                """);
        String drinkSize = scan.nextLine();
        System.out.println("""
                        \n
                        Select flavor of your drink from following:
                        Cola
                        Orange
                        Lemon
                        Root Beer
                """);
        String drinkFlavor = scan.nextLine();

        Drink drink = new Drink(drinkSize, drinkFlavor);
        order.addMenuItem(drink);
    }

    private void addGarlicKnotsToOrder(Order order){
        GarlicKnots garlicKnots = new GarlicKnots("regular", "garlic knots");
        order.addMenuItem(garlicKnots);
    }

    public void checkout(Order order){
        boolean hasMenuItems = true;

        while(hasMenuItems){

            if(order.getOrderedItems().isEmpty()){
                System.out.println("""
            \nYou have nothing added to your order!
            Please make at least one item (Pizza, Drink or Garlic Knots to you order.)
            Would you like to continue adding items? (yes/no)?
            """);
            String response = scan.nextLine().toLowerCase().trim();
            if(response.equalsIgnoreCase("yes")){
                return;
            }
            return;//
            }
            //Order summary review
            displayOrderSummary(order);

            //Get confirmation
            System.out.println("\n===============================================================================");
            System.out.println("1. Confirm Order");
            System.out.println("2. Cancel Order");
            System.out.println("===============================================================================");
            System.out.println("Please enter your choice: ");
            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "confirm" -> {
                    Receipt receipt = new Receipt(order);
                    if (receipt.saveReceipt()) {
                        System.out.println("\nOrder confirmed! Receipt saved.");
                        System.out.println("Thank you for your order! 🍕");
                    }
                    hasMenuItems = false;
                }
                case "2", "cancel" -> {
                    System.out.println("\n Order cancelled.");
                    hasMenuItems = false;
                }
                default -> System.out.println("Invalid input! Try again.");
            }
        }

    }

    public void displayOrderSummary(Order order){
        System.out.println("\n===============================================================================");
        System.out.println("                        ORDER SUMMARY");
        System.out.println("===============================================================================");
        System.out.println("Customer: " + order.getCustomerName() + "\n");

        List<MenuItem> items = order.getOrderedItemsList();
        int itemNumber = 1;

        for(MenuItem item : items){
            if(item instanceof Pizza){
                Pizza pizza = (Pizza) item;
                System.out.println(itemNumber + ". PIZZA");
                System.out.println(" Size: " + pizza.getSize());
                System.out.println(" Crust: " + pizza.getCrust());

                Map<String, List<String>> toppings = pizza.getSelectedOptions();
                System.out.println("  Toppings: ");
                for(String category : toppings.keySet()){
                    List<String> sameCategoryToppings = toppings.get(category);
                    if(!sameCategoryToppings.isEmpty()){
                        System.out.println("   " + category + ": " + sameCategoryToppings);
                    }
                }
                System.out.println("  Price: $"+ String.format("%.2f", pizza.getPrice()) + "\n");

            } else if (item instanceof Drink) {
                Drink drink = (Drink) item;
                System.out.println(itemNumber + ". DRINK - " + drink.getFlavor());
                System.out.println(" Size: " + drink.getSize());
                System.out.println(" Price: $" + String.format("%.2f", drink.getPrice()));

            } else if (item instanceof GarlicKnots){
                System.out.println(itemNumber + ". GARLIC KNOTS");
                System.out.println(" Price: $" + item.getPrice());
            }
            itemNumber++;
        }
        System.out.println("===============================================================================");
        System.out.println("TOTAL: $" + String.format("%.2f", order.getPrice()));
        System.out.println("===============================================================================\n");

    }



}