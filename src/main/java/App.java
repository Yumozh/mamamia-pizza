
import model.Pizza;
import model.Topping;
import ui.UserInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.display();
        userInterface.runHomeScreen();

//        Map<String, Topping> pizzaMenu = new HashMap<>();
//
//
//        Pizza pizza = new Pizza("8", "regular", "thin");
//        pizza.addTopping("meat", "pepperoni");
//        pizza.addTopping("cheese", "mozzarella");
//        pizza.addTopping("sauces", "pesto");
//
//        System.out.println(pizza.calculatePrice("8"));

    }
}
