package model;

public class MargheritaPizza extends Pizza{

    public MargheritaPizza() {
        super("12", "regular");
        addTopping("cheese", "mozzarella");
        addTopping("regular", "tomatoes");
        addTopping("regular", "basil");
        addTopping("sauces", "marinara");
        addTopping("sauces", "olive oil");
    }
}
