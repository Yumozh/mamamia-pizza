package model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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

    private ToppingCatalog() {} // prevent instantiation
}