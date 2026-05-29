package model;

public class MargheritaPizza extends Pizza{

    public MargheritaPizza() {
        super("12", "regular");

        addTopping(Category.CHEESE, "mozzarella");
        addTopping(Category.REGULAR, "tomatoes");
        addTopping(Category.REGULAR, "basil");
        addTopping(Category.SAUCES, "marinara");
        addTopping(Category.SAUCES, "olive oil");
    }
}
