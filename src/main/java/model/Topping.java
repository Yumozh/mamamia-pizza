package model;

import java.util.List;

public class Topping{
    private String category;
    private boolean isPremium;
    private List<String> categoryOption;

    public Topping(String category,boolean isPremium, List<String> categoryOption) {
        this.category = category;
        this.isPremium = isPremium;
        this.categoryOption = categoryOption;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getCategoryOption() {
        return categoryOption;
    }

    public void setCategoryOption(List<String> categoryOption) {
        this.categoryOption = categoryOption;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
    public double calculatePrice(String pizzaSize){
        double price = 0;
        if(isPremium){
            if(this.category.equalsIgnoreCase("meat")){
               price = switch(pizzaSize){
                    case "8", "Personal" -> 1.0;
                    case "12", "Medium" -> 2.0;
                    case "16", "Large" -> 3.0;
                    default -> throw new IllegalStateException("Unknown pizza size");
               };
            }
            if(this.category.equalsIgnoreCase("cheese")){
                price = switch(pizzaSize){
                    case "8", "Personal" -> 0.75;
                    case "12", "Medium" -> 1.5;
                    case "16", "Large" -> 2.25;
                    default -> throw new IllegalStateException("Unknown pizza size");
                };
            }
        } else{
            price = 0;
        }
        return price;
    }

}
