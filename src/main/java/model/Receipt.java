package model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Receipt {
    private Order order;
    private String receiptsFolder;

    public Receipt(Order order) {
        this.order = order;
        this.receiptsFolder = "src/main/java/data";
    }

    public String generateReceiptContent() {
        StringBuilder receipt = new StringBuilder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

        receipt.append("===============================================================================\n");
        receipt.append("                        PIZZA MAMA MIA - RECEIPT\n");
        receipt.append("===============================================================================\n\n");

        receipt.append("Customer: ").append(order.getCustomerName()).append("\n");
        receipt.append("Date/Time: ").append(order.getOrderTime().format(dateFormatter)).append("\n");
        receipt.append("Order #: ").append(getReceiptFileName().replace(".txt", "")).append("\n\n");

        receipt.append("===============================================================================\n");
        receipt.append("ITEMS ORDERED:\n");
        receipt.append("===============================================================================\n\n");

        int itemNumber = 1;
        for (MenuItem item : order.getOrderedItems()) {
            if (item instanceof Pizza) {
                receipt.append(formatPizzaForReceipt((Pizza) item, itemNumber));
                itemNumber++;
            } else if (item instanceof Drink) {
                receipt.append(formatDrinkForReceipt((Drink) item, itemNumber));
                itemNumber++;
            } else if (item instanceof GarlicKnots) {
                receipt.append(formatGarlicKnotsForReceipt(item, itemNumber));
                itemNumber++;
            }
        }

        receipt.append("===============================================================================\n");
        receipt.append("TOTAL: $").append(String.format("%.2f", order.getPrice())).append("\n");
        receipt.append("===============================================================================\n\n");
        receipt.append("Thank you for your order! Come again soon! 🍕\n");

        return receipt.toString();
    }

    private String formatPizzaForReceipt(Pizza pizza, int itemNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(itemNumber).append(". PIZZA\n");
        sb.append("   Size: ").append(pizza.getSize()).append("\n");
        sb.append("   Crust: ").append(pizza.getCrust()).append("\n");

        // List toppings
        Map<Category, List<String>> toppings = pizza.getSelectedOptions();
        if (hasAnyToppings(toppings)) {
            sb.append("   Toppings:\n");
            for (Category category : toppings.keySet()) {
                List<String> items = toppings.get(category);
                if (!items.isEmpty()) {
                    sb.append("     • ").append(category)
                            .append(": ").append(String.join(", ", items)).append("\n");
                }
            }
        }

        sb.append("   Base Price: $").append(String.format("%.2f", pizza.getBasePrice())).append("\n");
        sb.append("   Toppings Total: $").append(String.format("%.2f", pizza.calculateToppingTotal())).append("\n");
        sb.append("   Item Total: $").append(String.format("%.2f", pizza.getPrice())).append("\n\n");
        return sb.toString();
    }

    private String formatDrinkForReceipt(Drink drink, int itemNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(itemNumber).append(". DRINK\n");
        sb.append("   Flavor: ").append(drink.getFlavor()).append("\n");
        sb.append("   Size: ").append(drink.getSize()).append("\n");
        sb.append("   Price: $").append(String.format("%.2f", drink.getPrice())).append("\n\n");
        return sb.toString();
    }

    private String formatGarlicKnotsForReceipt(MenuItem item, int itemNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(itemNumber).append(". GARLIC KNOTS\n");
        sb.append("   Price: $").append(String.format("%.2f", item.getPrice())).append("\n\n");
        return sb.toString();
    }

    private boolean hasAnyToppings(Map<Category, List<String>> toppings) {
        for (List<String> items : toppings.values()) {
            if (!items.isEmpty()) return true;
        }
        return false;
    }
    public boolean saveReceipt() {
        try {
            String fileName = receiptsFolder + "/" + getReceiptFileName();

            // Use try-with-resources to auto-close the file
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(generateReceiptContent());
            }

            System.out.println("Receipt saved to: " + fileName);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
            return false;
        }
    }
    private String getReceiptFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        return order.getOrderTime().format(formatter) + ".txt";
    }
}
