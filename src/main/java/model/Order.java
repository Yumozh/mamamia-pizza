package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order{
    private String customerName;
    private LocalDate orderTime;
    private List<MenuItem> orderedItemsList;

    public Order(String customerName) {
        this.customerName = customerName;
        this.orderedItemsList = new ArrayList<MenuItem>();
    }

    public LocalDate getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDate orderTime) {
        this.orderTime = orderTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void addMenuItem(MenuItem item){
        orderedItemsList.add(item);
    }
    public void removeMenuItem(MenuItem item){
        orderedItemsList.remove(item);
    }

    public double getTotalPrice() {
        return this.orderedItemsList.stream()
                .mapToDouble(MenuItem::getTotalPrice)
                .sum();
    }
}
