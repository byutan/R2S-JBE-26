package session01;

import java.util.Scanner;


public class StudentManagement {
    public static void initStudent(Student student, int id){
        Scanner scanner = new Scanner(System.in);

        student.id = id;

        while(true) {
            System.out.print("Enter gender: ");
            String gender = scanner.nextLine();
            if (gender.equals("male") || gender.equals("female")) {
                student.gender = gender;
                break;
            } else {
                System.out.println("Invalid gender");
            }
        }

        while(true) {
            System.out.print("Enter age: ");
            byte age = scanner.nextByte();
            scanner.nextLine();

            if(age >= 18){
                student.age = age;
                break;
            } else {
                System.out.println("Invalid age");
            }
        }

        while(true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            if(!name.isEmpty()){
                student.name = name;
                break;
            } else {
                System.out.println("Invalid name");
            }
        }

        System.out.print("Enter address: ");
        student.address = scanner.nextLine();
        System.out.print("Enter email: ");
        student.email = scanner.nextLine();
    }

    public static void displayAllStudent(Student[] students, int size) {
        if(size == 0) {
            System.out.println("Nothing to display");
            return;
        } else {
            for(int i = 0; i < size; i++) {
                System.out.printf("ID: %d, ", students[i].id);
                System.out.printf("Name: %s, ", students[i].name);
                System.out.printf("Age: %d, ", students[i].age);
                System.out.printf("Address: %s, ", students[i].address);
                System.out.printf("Gender: %s, ", students[i].gender);
                if(i == size - 1) {
                    System.out.printf("Email: %s%n", students[i].email);
                } else {
                    System.out.printf("Email: %s,%n", students[i].email);
                }
            }
        }
    }

    public static void findStudentById(Student[] students, int size, int idx) {
        if(size == 0) {
            System.out.println("Nothing to display");
        } else {
            for(int i = 0; i < size; i++) {
                if (i == idx) {
                    System.out.printf("ID: %d, ", students[i].id);
                    System.out.printf("Name: %s, ", students[i].name);
                    System.out.printf("Age: %d, ", students[i].age);
                    System.out.printf("Address: %s, ", students[i].address);
                    System.out.printf("Gender: %s, ", students[i].gender);
                    System.out.printf("Email: %s%n", students[i].email);
                    return;
                }
            }
            System.out.println("Nothing to display");
        }
    }

    public static void updateStudentById(Student[] students, int size, int idx) {
        if(size == 0) {
            System.out.println("Nothing to display");
        } else {
            for(int i = 0; i < size; i++) {
                if (i == idx) {
                    initStudent(students[i], idx);
                    return;
                }
            }
            System.out.println("Nothing to display");
        }
    }

    public static void main(String[] args) {
        Student[] students = new Student[100];

        Scanner scanner = new Scanner(System.in);

        String choice;
        byte count = 0;

        do {
            System.out.println("1. Add student" );
            System.out.println("2. Display all students" );
            System.out.println("3. Find student by id" );
            System.out.println("4. Update student by id" );
            System.out.println("5. Quit" );

            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    Student student = new Student(); // initialize object
                    initStudent(student, count); // initialize attributes
                    students[count] = student; // add object to array
                    count++;
                    break;
                }

                case "2": {
                    displayAllStudent(students, count);
                    break;
                }

                case "3": {
                    System.out.print("Enter student id: ");
                    byte index = scanner.nextByte();
                    scanner.nextLine();
                    findStudentById(students, count, index);
                    break;
                }

                case "4": {
                    System.out.print("Enter student id: ");
                    byte index = scanner.nextByte();
                    scanner.nextLine();
                    updateStudentById(students, count, index);
                    break;
                }
            }
        } while (!choice.equals("5"));
    }
}
