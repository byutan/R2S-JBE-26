package session05;

import java.util.Scanner;

public class ProductManagement {
    private Product[] products;
    private byte count = 0;
    private final byte MAX = 10;

    public ProductManagement() {
        this.products = new Product[MAX];
    }

    public void addProduct() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter product Name: ");
        String name = sc.nextLine();

        System.out.println("Enter product Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter product Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        Product newProduct = new Product(id, name, price, quantity);

        if(count == MAX) {
            throw new IllegalArgumentException("Maximum products exceeded");
        }
        if (count > 0) {
            for(Product p : products) {
                if(p.getProductID() == newProduct.getProductID()) {
                    throw new IllegalArgumentException("Product with ID " + newProduct.getProductID() + " already exists");
                }
            }
        }
        products[count++] = newProduct;
        System.out.println("Product added successfully");
    }

    public Product retrieveProduct() throws ProductNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if(count > 0) {
            for(Product p : products) {
                if(p.getProductID() == id) {
                    System.out.println("Product with ID" + id + " found");
                    return p;
                }
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found");
    }

    public void updateStock() throws ProductNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if(count > 0) {
            for(Product p : products) {
                if(p.getProductID() == id) {
                    System.out.println("Enter product Quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    p.setQuantityInStock(quantity);
                    System.out.println("Quantity updated successfully.");
                    return;
                }
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found");
    }
}
