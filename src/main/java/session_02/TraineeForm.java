package session_02;

import java.util.Scanner;

public class TraineeForm {
    private Scanner scanner = new Scanner(System.in);

    public TraineeForm(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getId() {
        return scanner.nextLine();
    }

    public Trainee getTrainee() {
        System.out.println("Enter trainee's name: ");
        String name = scanner.nextLine();
        System.out.println("Enter trainee's age: ");
        byte age = scanner.nextByte();
        scanner.nextLine();
        System.out.println("Enter trainee's gender: ");
        String gender = scanner.nextLine();

        Trainee trainee = new Trainee();


        if(!name.isEmpty() && (gender.equals("Male") || gender.equals("Female")) && (age >= 6)) {
            trainee.setName(name);
            trainee.setGender(gender);
            trainee.setAge(age);
        } else {
            return null;
        }

        return trainee;
    }
}