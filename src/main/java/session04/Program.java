package session04;

import java.util.Scanner;

public class Program {
    private Product[] products;
    private byte numOfProduct;
    private final byte MAX = 100;

    public Program() {
        products = new Product[MAX];
    }

    public void addProduct(Product product) {
        if(products == null) {
            throw new IllegalArgumentException("Product is null");
        } else if(numOfProduct >= MAX) {
            System.out.println("Maximum number of products reached");
        } else {
            System.out.println("Product added");
            products[numOfProduct++] = product;
        }
    }

    public void displayProducts() {
        if(products == null) {
            throw new IllegalArgumentException("Product list is null");
        } else if(numOfProduct == 0) {
            System.out.println("No product to display");
        } else {
            for(int i = 0; i < numOfProduct; i++) {
                System.out.println(products[i].toString());
            }
        }
    }

    public Product findProduct(int id) {
        if(products == null) {
            throw new IllegalArgumentException("Product list is null");
        }  else if(id < 0) {
            System.out.println("Product id is negative");
            return null;
        }
        for(int i = 0; i < numOfProduct; i++) {
            if(products[i].getId() == id) {
                return products[i];
            }
        }
        System.out.println("Product not found");
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Program p = new Program();

        while (true) {
            System.out.println("----PROGRAM----");
            System.out.println("1. Add electronic product");
            System.out.println("2. Add clothing product");
            System.out.println("3. Display products");
            System.out.println("4. Find product by ID");
            System.out.println("5. Quit");

            byte option =  sc.nextByte();
            sc.nextLine();

            switch (option) {
                case 1, 2:
                    System.out.print("Enter product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter product price: ");
                    float price = sc.nextFloat();
                    sc.nextLine();

                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    if(option == 1) {
                        System.out.print("Enter product brand: ");
                        String brand = sc.nextLine();

                        Electronics electronic = new Electronics(id, name, price, brand);
                        p.addProduct(electronic);
                    } else {
                        System.out.print("Enter product size: ");
                        String size = sc.nextLine();

                        Clothing clothing = new Clothing(id, name, price, size);
                        p.addProduct(clothing);
                    }
                    break;

                case 3:
                    p.displayProducts();
                    break;

                case 4:
                    System.out.print("Enter product ID: ");
                    int input_id = sc.nextInt();
                    sc.nextLine();

                    System.out.println(p.findProduct(input_id).toString());
                    break;

                case 5:
                    return;

                default:
                    throw new IllegalArgumentException("Invalid option");
            }
        }
    }
}
