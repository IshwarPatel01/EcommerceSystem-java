package models;

import java.util.ArrayList;

public class Customer {
    private String name;
    private ArrayList<Order> orderHistory = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void placeOrder(Product product, int quantity) {
        Order order = new Order(product, quantity);
        if (order.isValidOrder()) {
            order.updateStock();
            orderHistory.add(order);
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Error: Insufficient stock for this product.");
        }
    }

    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders yet.");
        } else {
            for (Order order : orderHistory) {
                order.displayOrder();
            }
        }
    }

    public String getName() {
        return name;
    }
}
