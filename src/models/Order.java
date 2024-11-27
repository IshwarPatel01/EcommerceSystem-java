package models;

public class Order {
    private Product product;
    private int quantity;
    private double totalPrice;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getPrice() * quantity;
    }

    public void displayOrder() {
        System.out.println("Ordered: " + product.getName() + " | Quantity: " + quantity + " | Total: $" + totalPrice);
    }

    public boolean isValidOrder() {
        return product.getStockQuantity() >= quantity;
    }

    public void updateStock() {
        product.updateStock(-quantity); // Decrease stock
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
