package services;

import models.Product;
import models.Order;
import models.Customer;
import models.Admin;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class EcommerceSystem {
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private Admin admin = new Admin("admin", "password123");

    public void run() {
        Scanner sc = new Scanner(System.in);

        // Initial products
        products.add(new Product("Laptop", "Electronics", 800.00, 10));
        products.add(new Product("T-Shirt", "Clothing", 25.00, 50));
        products.add(new Product("Smartphone", "Electronics", 600.00, 5));

        System.out.println("Welcome to the E-commerce System!");
        System.out.println("Please enter your name: ");
        String name = sc.nextLine();
        Customer customer = new Customer(name);
        customers.add(customer);

        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View All Products");
            System.out.println("2. Place an Order");
            System.out.println("3. View Order History");
            System.out.println("4. Admin Login");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // View all products
                    for (Product product : products) {
                        product.displayProduct();
                    }
                    pause();  // Pause here after displaying products
                    break;

                case 2:
                    // Place an order
                    System.out.print("Enter product name: ");
                    String productName = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine(); // Consume newline

                    Product productToOrder = null;
                    for (Product product : products) {
                        if (product.getName().equalsIgnoreCase(productName)) {
                            productToOrder = product;
                            break;
                        }
                    }

                    if (productToOrder != null) {
                        customer.placeOrder(productToOrder, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    pause();  // Pause here after placing order
                    break;

                case 3:
                    // View order history
                    customer.viewOrderHistory();
                    pause();  // Pause here after viewing order history
                    break;

                case 4:
                    // Admin login
                    System.out.print("Enter admin username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter admin password: ");
                    String password = sc.nextLine();

                    if (admin.authenticate(username, password)) {
                        System.out.println("Admin logged in successfully!");
                        adminMenu(sc);
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    pause();  // Pause here after admin login attempt
                    break;

                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

        sc.close();
    }

    private void adminMenu(Scanner sc) {
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Restock Product");
            System.out.println("4. View All Products");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter stock quantity: ");
                    int stockQuantity = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    admin.addProduct(products, name, category, price, stockQuantity);
                    break;

                case 2:
                    System.out.print("Enter product name to remove: ");
                    String removeProductName = sc.nextLine();
                    admin.removeProduct(products, removeProductName);
                    break;

                case 3:
                    System.out.print("Enter product name to restock: ");
                    String restockProductName = sc.nextLine();
                    Product productToRestock = null;
                    for (Product product : products) {
                        if (product.getName().equalsIgnoreCase(restockProductName)) {
                            productToRestock = product;
                            break;
                        }
                    }

                    if (productToRestock != null) {
                        System.out.print("Enter quantity to add: ");
                        int restockQuantity = sc.nextInt();
                        sc.nextLine(); // Consume newline
                        admin.restockProduct(productToRestock, restockQuantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    admin.viewAllProducts(products);
                    break;

                case 5:
                    adminRunning = false;
                    System.out.println("Logged out as admin.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }
}
