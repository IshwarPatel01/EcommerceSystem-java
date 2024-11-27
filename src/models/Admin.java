package models;

import java.util.ArrayList;

public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    public void addProduct(ArrayList<Product> products, String name, String category, double price, int stockQuantity) {
        if (price < 0 || stockQuantity < 0) {
            System.out.println("Price and stock quantity must be non-negative.");
            return;
        }
        Product newProduct = new Product(name, category, price, stockQuantity);
        products.add(newProduct);
        System.out.println("Product added successfully!");
    }

    public void removeProduct(ArrayList<Product> products, String productName) {
        boolean found = false;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                products.remove(product);
                System.out.println("Product removed successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found.");
        }
    }

    public void restockProduct(Product product, int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity must be positive.");
            return;
        }
        product.updateStock(quantity);
        System.out.println("Product restocked successfully!");
    }

    public void viewAllProducts(ArrayList<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                product.displayProduct();
            }
        }
    }
}
