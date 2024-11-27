package models;

public class Product {
    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    public Product(String name, String category, double price, int stockQuantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void updateStock(int quantity) {
        if (quantity >= 0) {
            this.stockQuantity += quantity;
        }
    }

    public void displayProduct() {
        System.out.println("Product: " + name + " | Category: " + category + " | Price: $" + price + " | Stock: " + stockQuantity);
    }
}
