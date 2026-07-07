package session05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ProductNotFoundException {
        Scanner sc = new Scanner(System.in);
        ProductManagement productManagement = new ProductManagement();
        do {
            System.out.println("1. Add Product");
            System.out.println("2. Retrieve Product by ID");
            System.out.println("3. Update Stock by ID");
            System.out.println("4. Quit");

            byte option = sc.nextByte();
            sc.nextLine();

            try {
                switch (option) {
                    case 1:
                        productManagement.addProduct();
                        break;
                    case 2:
                        Product foundProduct = productManagement.retrieveProduct();
                        if(foundProduct == null){
                            break;
                        } else {
                            System.out.println(foundProduct.displayProductInfo());
                        }
                        break;
                    case 3:
                        productManagement.updateStock();
                        break;
                    case 4:
                        return;
                    case 5:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (InputMismatchException | ProductNotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }
}
