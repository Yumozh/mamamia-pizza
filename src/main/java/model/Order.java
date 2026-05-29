package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Priceable{
    private String customerName;
    private LocalDateTime orderTime;
    private List<MenuItem> orderedItemsList;

    public Order(String customerName) {
        this.customerName = customerName;
        this.orderTime = LocalDateTime.now();
        this.orderedItemsList = new ArrayList<MenuItem>();
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public double getPrice(){
        return orderedItemsList.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<MenuItem> getOrderedItemsList() {
        return orderedItemsList;
    }

    public void setOrderedItemsList(List<MenuItem> orderedItemsList) {
        this.orderedItemsList = orderedItemsList;
    }

    public void addMenuItem(MenuItem item){
        orderedItemsList.add(item);
        System.out.println("Item was added to your order!");
    }
    public void removeMenuItem(MenuItem item){
        orderedItemsList.remove(item);
    }

    public List<MenuItem> getOrderedItems() {
        return orderedItemsList;
    }

}
