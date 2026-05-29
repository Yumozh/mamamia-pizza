# Mamamia Pizza — Pizza Store Application

## Project Description

Mamamia Pizza is a Java console-based point-of-sale application for a custom pizza shop. The application allows customers to create a full order by adding customized pizzas, drinks, and garlic knots. It guides the user through each step of the ordering process, calculates the total price, displays the order summary at checkout, and saves a receipt file for each completed order.

This project was built using Object-Oriented Programming principles. The application separates responsibilities across classes such as orders, pizzas, toppings, drinks, garlic knots, receipts, menus, and pricing logic.

---
## Main Features
### Home Screen
The application starts with a home screen where the user can:

- Start a new order
- Exit the application

The program continues running until the user chooses to exit.

### Order Screen
After starting a new order, the user can:

- Add a pizza
- Add a drink
- Add garlic knots
- Checkout
- Cancel the order and return to the home screen
### Custom Pizza Builder
The pizza builder walks the user through the pizza customization process one pizza at a time.
### Topping Categories and Pricing
One interesting challenge I noticed while building this project was that toppings are grouped by category and by premium/non-premium type, which directly impacts the final pizza price.

For example:

- Meat and cheese are premium toppings.
- Extra meat and extra cheese cost more depending on pizza size.
- Regular toppings, sauces, and sides are included in the pizza price.

To solve this cleanly, I used a `HashMap` / `EnumMap` approach with an enum-based category system. This made the topping catalog easier to organize, easier to validate, and easier to connect to pricing rules.

```java
public class ToppingCatalog {

    private static final List<String> MEAT_OPTIONS = List.of(
            "pepperoni", "sausage", "ham", "bacon", "chicken", "meatball");

    private static final List<String> CHEESE_OPTIONS = List.of(
            "Mozzarella", "Parmesan", "Ricotta", "Goat Cheese", "Buffalo");

    private static final List<String> REGULAR_OPTIONS = List.of(
            "onions", "mushrooms", "bell peppers", "olives",
            "tomatoes", "spinach", "basil", "pineapple", "anchovies");

    private static final List<String> SAUCE_OPTIONS = List.of(
            "marinara", "alfredo", "pesto", "bbq", "buffalo", "olive oil");

    private static final List<String> SIDE_OPTIONS = List.of(
            "red pepper", "parmesan");

    public static final Map<Category, ToppingCategory> CATEGORIES;

    static {
        Map<Category, ToppingCategory> map = new EnumMap<>(Category.class);

        map.put(Category.MEAT, new ToppingCategory("meat", new ArrayList<>(MEAT_OPTIONS)));
        map.put(Category.EXTRA_MEAT, new ToppingCategory("extra meat", new ArrayList<>(MEAT_OPTIONS)));

        map.put(Category.CHEESE, new ToppingCategory("cheese", new ArrayList<>(CHEESE_OPTIONS)));
        map.put(Category.EXTRA_CHEESE, new ToppingCategory("extra cheese", new ArrayList<>(CHEESE_OPTIONS)));

        map.put(Category.REGULAR, new ToppingCategory("regular", new ArrayList<>(REGULAR_OPTIONS)));
        map.put(Category.SAUCES, new ToppingCategory("sauces", new ArrayList<>(SAUCE_OPTIONS)));
        map.put(Category.SIDES, new ToppingCategory("sides", new ArrayList<>(SIDE_OPTIONS)));

        CATEGORIES = Map.copyOf(map); // immutable — catalog never changes at runtime
    }
}
```
### Checkout
At checkout, the application displays:

- All pizzas in the order
- Pizza size, crust, toppings, sauces, sides, and stuffed crust option
- Drinks
- Garlic knots
- Total order price
## Bonus Features

### Signature Pizzas
The project includes support for signature pizza templates. Signature pizzas give customers a ready-made pizza option that they can customize further by adding or removing toppings.

Examples of possible signature pizzas:

#### Margherita
#### Veggie Pizza

---

## Program Architecture Diagram
![Mamamia-Pizza Diagram.drawio.png](Mamamia-Pizza%20Diagram.drawio.png)