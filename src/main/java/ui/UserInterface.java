package ui;

import model.Order;
import model.Pizza;
import model.Topping;
import model.MenuItem;

import java.util.ArrayList;
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

        Pizza pizza = new Pizza(sizePizza, "regular", crust);
        addToppings(pizza);

    }
    public void addToppings(Pizza pizza){
//        System.out.println("""
//                \n
//                Select one type of meat from following:
//                1 - Pepperoni
//                1 - Sausage
//                2 - Ham
//                3 - Bacon
//                4 - Chicken
//                4 - Meatball
//                """);
//        String meat = scan.nextLine();
        boolean isRunning = true;
        System.out.println("""
                \n
                Select one type of meat from following:
                1 - Meat
                1 - Cheese
                2 - Regular
                3 - Sauces
                4 - Sides
                """);

        while(isRunning){
            String choice = scan.nextLine().toLowerCase().trim();

            switch (choice){
                case "1" -> {
                    Topping meatTopping = new Topping(pizza.getSize(), "Meat",true);
                }
                case "2" -> {
                    Topping cheeseTopping = new Topping(pizza.getSize(), "Cheese",true );
                }
                case "3" -> {
                    Topping regularTopping = new Topping(pizza.getSize(), "Regular",true );
                }
                case "X", "Exit" -> isRunning = false;
                default -> throw new IllegalStateException ("Invalid input! Try again.");
            }
        }
    }
}
