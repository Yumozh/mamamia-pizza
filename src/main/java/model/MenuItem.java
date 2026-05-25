package model;

public abstract class MenuItem {
    protected String size;
    private String itemName;

    public MenuItem(String size, String itemName) {
        this.size = size;
        this.itemName = itemName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public abstract double calculatePrice(String Size);
}
