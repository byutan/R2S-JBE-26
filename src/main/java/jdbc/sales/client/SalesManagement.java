package jdbc.sales.client;

import jdbc.sales.dao.CustomerDAO;
import jdbc.sales.dao.EmployeeDAO;
import jdbc.sales.entities.Customer;
import jdbc.sales.entities.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalesManagement {
    private static Scanner sc;
    private CustomerDAO customerDAO;
    private CustomerForm customerForm;
    private EmployeeDAO employeeDAO;
    private  EmployeeForm employeeForm;

    public SalesManagement() {
        sc = new Scanner(System.in);
        Connection conn = getConnection();
        customerDAO = new CustomerDAO(conn);
        customerForm = new CustomerForm(sc);
        employeeDAO = new EmployeeDAO(conn);
        employeeForm = new EmployeeForm(sc);
    }

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sales", "root", "Kho@0104");
            if(conn != null) {
                System.out.println("Connected to database.");
            } else {
                System.out.println("Unable to connect to database.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    private void displayAllCustomers() {
        ArrayList<Customer> customerList = customerDAO.selectAll();
        if(customerList == null || customerList.isEmpty()) {
            System.out.println("There are no customers.");
            return;
        }
        for(Customer c : customerList) {
            System.out.println(c.toString());
        }
    }

    private void addCustomer() {
        Customer newCustomer = customerForm.getCustomer();
        boolean isInserted = customerDAO.insert(newCustomer);
        if(isInserted) {
            System.out.println("Customer inserted successfully");
        } else {
            System.out.println("Failed to insert customer");
        }
    }

    private void updateCustomer() {
        int id = customerForm.getId();
        Customer customerToUpdate =  customerForm.getCustomer();
        boolean isUpdated = customerDAO.update(id, customerToUpdate);
        if(isUpdated) {
            System.out.println("Customer updated successfully");
        } else {
            System.out.println("Failed to update customer");
        }
    }

    private void removeCustomer() {
        int id = customerForm.getId();
        boolean isDeleted = customerDAO.delete(id);
        if(isDeleted) {
            System.out.println("Customer deleted successfully");
        } else {
            System.out.println("Failed to delete customer");
        }
    }

    private void displayAllEmployee() {
        ArrayList<Employee> employeeList = employeeDAO.selectAll();
        if(employeeList == null || employeeList.isEmpty()) {
            System.out.println("There are no employees.");
            return;
        }
        for(Employee e : employeeList) {
            System.out.println(e.toString());
        }
    }

    private void addEmployee() {
        Employee newEmployee = employeeForm.getEmployee();
        boolean isInserted = employeeDAO.insert(newEmployee);
        if(isInserted) {
            System.out.println("Employee inserted successfully");
        } else {
            System.out.println("Failed to insert employee");
        }
    }

    private void removeEmployee() {
        int id = employeeForm.getId();
        boolean isDeleted = employeeDAO.delete(id);
        if(isDeleted) {
            System.out.println("Employee deleted successfully");
        } else {
            System.out.println("Failed to delete employee");
        }
    }

    private void updateEmployee() {
        int id = employeeForm.getId();
        Employee updateEmployee = employeeForm.getEmployee();
        boolean isUpdated =  employeeDAO.update(id, updateEmployee);
        if(isUpdated) {
            System.out.println("Employee updated successfully");
        } else {
            System.out.println("Failed to update employee");
        }
    }

    public static void main(String[] args) {
        SalesManagement salesManagement = new SalesManagement();
        Scanner sc = new Scanner(System.in);
        byte option = 0;
        do {
            System.out.println("1. Get all customers");
            System.out.println("2. Add new customer");
            System.out.println("3. Change customer information");
            System.out.println("4. Remove an customer");
            System.out.println("5. Get all employees");
            System.out.println("6. Add new an employee");
            System.out.println("7. Change employee information");
            System.out.println("8. Remove an employee");
            System.out.println("0. Quit");
            System.out.print("Your choice: ");

            option = sc.nextByte();
            sc.nextLine();
            try {
                switch (option) {
                    case 1 -> salesManagement.displayAllCustomers();
                    case 2 -> salesManagement.addCustomer();
                    case 3 -> salesManagement.updateCustomer();
                    case 4 -> salesManagement.removeCustomer();
                    case 5 -> salesManagement.displayAllEmployee();
                    case 6 -> salesManagement.addEmployee();
                    case 7 -> salesManagement.updateEmployee();
                    case 8 -> salesManagement.removeEmployee();
                    case 0 -> { return; }
                    default -> {}
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
}
