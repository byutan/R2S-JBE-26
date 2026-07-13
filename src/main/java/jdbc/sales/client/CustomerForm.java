package jdbc.sales.client;

import jdbc.sales.entities.Customer;

import java.util.Scanner;

public class CustomerForm {
    private Scanner sc;

    public CustomerForm(Scanner sc) {
        this.sc = sc;
    }

    public int getId() {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    public Customer getCustomer() {
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Customer Contact: ");
        String contact = sc.nextLine().trim();

        System.out.print("Enter Customer Address: ");
        String address = sc.nextLine().trim();

        System.out.print("Enter Customer City: ");
        String city = sc.nextLine().trim();

        System.out.print("Enter Customer Postal Code: ");
        String postalCode = sc.nextLine().trim();

        System.out.print("Enter Customer Country: ");
        String country = sc.nextLine().trim();

        if(!(name.trim().isEmpty()
                || contact.trim().isEmpty()
                || address.trim().isEmpty()
                || city.trim().isEmpty()
                || postalCode.trim().isEmpty()
                || country.trim().isEmpty())) {
            return new Customer(name, contact, address, city, postalCode, country);
        }
        throw new IllegalArgumentException("Invalid Input");
    }
}
