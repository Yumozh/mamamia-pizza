package model;

public class VeggiePizza extends Pizza{
    public VeggiePizza() {
        super("8", "regular");

        addTopping(Category.REGULAR, "bell peppers");
        addTopping(Category.REGULAR, "spinach");
        addTopping(Category.REGULAR, "olives");
        addTopping(Category.REGULAR, "onions");
        addTopping(Category.SAUCES, "marinara");
        addTopping(Category.SAUCES, "mozzarella");
    }
}
