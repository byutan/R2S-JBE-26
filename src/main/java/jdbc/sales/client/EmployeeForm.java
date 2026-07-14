package jdbc.sales.client;

import jdbc.sales.entities.Employee;

import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeForm {
    private Scanner sc;

    public EmployeeForm(Scanner sc) {
        this.sc = sc;
    }

    public int getId() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    public Employee getEmployee() {
        System.out.print("Enter Employee Last Name: ");
        String lastName = sc.nextLine().trim();

        System.out.print("Enter Employee First Name: ");
        String firstName = sc.nextLine().trim();

        System.out.print("Enter Employee BirthDate Year (YYYY): ");
        String year = sc.nextLine().trim();
        if(year.length() != 4 || Integer.parseInt(year) < 0) {
            throw new IllegalArgumentException("Invalid Year");
        }
        System.out.print("Enter Employee BirthDate Month (MM): ");
        String month = sc.nextLine().trim();
        if(month.length() != 2 || Integer.parseInt(month) < 0) {
            throw new IllegalArgumentException("Invalid Month");
        }
        System.out.print("Enter Employee BirthDate Date (DD): ");
        String date = sc.nextLine().trim();
        if(date.length() != 2 || Integer.parseInt(date) < 0) {
            throw new IllegalArgumentException("Invalid Date");
        }
        LocalDate localDate = LocalDate.now();
        LocalDate inputDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
        if(inputDate.isAfter(localDate)) {
            throw new IllegalArgumentException("Invalid BirthDate");
        }
        String birthDate = year + "-" + month + "-" + date;

        System.out.print("Enter Employee Supervisor ID: ");
        int supervisor = sc.nextInt();
        sc.nextLine();

        if(!(lastName.isEmpty() || firstName.isEmpty())) {
            return new Employee(supervisor, birthDate, firstName, lastName);
        }
        throw new IllegalArgumentException("Invalid Input");
    }
}
