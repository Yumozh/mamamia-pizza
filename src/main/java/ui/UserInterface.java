package ui;

import model.Order;
import model.Pizza;
import model.Topping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    Scanner scan = new Scanner(System.in);

    public void display(){
        System.out.println("===============================================================================");
        System.out.println("               🍕 WELCOME TO THE PIZZA MAMA MIA APP! 🍕");
        System.out.println("===============================================================================\n");
    }
    public void runHomeScreen(){
        boolean isRunning = true;

        while(isRunning){
            System.out.println("Home Screen");
            System.out.println("""
                    \n
                    1. Make Order
                    x. Exit
                    """);
            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice){
                case "1", "Make Order", "Order" -> makeOrder();
                case "X", "Exit" -> isRunning = false;
                default -> throw new IllegalStateException ("Invalid input! Try again.");
            }
        }
    }

    public void makeOrder(){
        boolean inOrderScreen = true;

        System.out.println("Enter your name: ");
        String customerName = scan.nextLine();

        Order order = new Order(customerName);


        while(inOrderScreen) {
            System.out.println("===============================================================================");
            System.out.println("                     🛒 PLACE YOUR PIZZA ORDER 🛒");
            System.out.println("===============================================================================\n");

            System.out.println("""
                    \n 
                    1. Add Pizza
                    2. Add Drink
                    3. Add Garlic Knots
                    X. Exit
                    """);
            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice){
                case "1", "add pizza"-> addPizza();
                default -> throw new IllegalStateException ("Invalid input! Try again.");
            }
        }

    }

    public void addPizza(){
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


        addToppings(pizza);

    }
    public void addToppings(Pizza pizza){
        boolean isRunning = true;
        while(isRunning) {
            System.out.println("Topping Selection");
            System.out.println("""
                    \n
                    1. Add meat
                    2. Add cheese
                    3. Add regular toppings
                    4. Add sauces
                    5. Add sides
                    x. Exit
                    """);
            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice) {
                case "1", "add meat" -> displayToppingsOptions(pizza.getToppingsMap(), "meat", pizza);
                case "2", "add cheese" -> displayToppingsOptions(pizza.getToppingsMap(), "cheese", pizza);
                case "3", "add regular toppings" -> displayToppingsOptions(pizza.getToppingsMap(), "regular", pizza);
                case "4", "add sauces" -> displayToppingsOptions(pizza.getToppingsMap(), "sauces", pizza);
                case "5", "add sides" -> displayToppingsOptions(pizza.getToppingsMap(), "sides", pizza);
                case "x", "exit" -> isRunning = false;
                default -> throw new IllegalStateException("Invalid input! Try again.");
            }
        }
    }

    public void displayToppingsOptions(Map<String, Topping> toppingsMap, String category, Pizza pizza){
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
        }
        addToppingSelection(category, pizza, topping);
    }

    private void addToppingSelection(String category, Pizza pizza, Topping topping) {
        System.out.println("Enter B to go back to topping categories.");
        //Enter meat name or exit
        String choice = scan.nextLine();

        int totalOptions = topping.getCategoryOptions().size();

        if (choice.equalsIgnoreCase("B")) {
            return;
        }
        try {
            // Try parsing the input to an integer choice
            int choiceNumber = Integer.parseInt(choice);
            List<String> options = topping.getCategoryOptions();

            if (choiceNumber >= 0 && choiceNumber < totalOptions) {

                String selectedOptionName = options.get(choiceNumber);
                pizza.addTopping(category, selectedOptionName);

                System.out.println(selectedOptionName + " successfully added to your pizza!");

            } else {
                System.out.println("Invalid selection number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or 'B' to go back.");
        }
    }

    public void addAllToppings(){

    }

    }