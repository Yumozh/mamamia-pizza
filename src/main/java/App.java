
import model.Pizza;
import model.Topping;
import ui.UserInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
//        UserInterface userInterface = new UserInterface();
//        userInterface.display();
//        userInterface.runHomeScreen();

        Map<String, Topping> pizzaMenu = new HashMap<>();
        pizzaMenu = Pizza.getToppingsMap();

        Pizza pizza = new Pizza("8", "regular", "thin");

        Topping pep = pizzaMenu.get("meat");
        if (pep != null) {
            System.out.println("Expected: 1.0 | Actual: " + pep.calculatePrice("8"));
        } else {
            System.out.println("Error: 'pepperoni' key not found in Map!");
        }
    }
}
