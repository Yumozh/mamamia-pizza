package model;

public class VeggiePizza extends Pizza{
    public VeggiePizza() {
        super("8", "regular");

        addTopping("regular", "bell peppers");
        addTopping("regular", "spinach");
        addTopping("regular", "olives");
        addTopping("regular", "onions");
        addTopping("sauce", "marinara");
        addTopping("cheese", "mozzarella");
    }
}
