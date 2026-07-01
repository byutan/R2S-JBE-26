package session02;

import java.util.Scanner;

public class TrainingManagement {
    private Scanner scanner = new Scanner(System.in);
    private TraineeForm traineeForm = new TraineeForm(scanner);
    private Trainee[] listOfTrainee = new Trainee[100];

    private byte count;

    public TrainingManagement() {}

    static boolean isEmpty(byte count) {
        return count == 0;
    }

    public static void main(String[] args) {
        TrainingManagement trainingManagement = new TrainingManagement();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("__________NEMU__________");
            System.out.println("1. Create trainee");
            System.out.println("2. Display all trainees");
            System.out.println("3. Display trainee by id");
            System.out.println("4. Display trainees by name");
            System.out.println("5. Update trainee by id");
            System.out.println("6. Quit");

            byte choice = scanner.nextByte();
            scanner.nextLine();

            Trainee trainee = new Trainee();
            String id;
            String name;

            switch (choice) {
                case 1:
                    trainingManagement.addTrainee();
                    break;
                case 2:
                    trainingManagement.displayAllTrainee();
                    break;
                case 3:
                    System.out.println("Enter trainee's id: ");
                    id  = scanner.nextLine();

                    trainee = trainingManagement.findTraineeById(id);

                    if(trainee != null) {
                        System.out.println("ID: " + trainee.getId());
                        System.out.println("Name: " + trainee.getName());
                        System.out.println("Gender: " + trainee.getGender());
                        System.out.println("Age: " + trainee.getAge());
                    }
                    break;
                case 4:
                    System.out.println("Enter trainee's name: ");
                    name = scanner.nextLine();
                    if(name.isEmpty()) {
                        System.out.println("Trainee's name is empty");
                        break;
                    }
                    Trainee[] traineeWithSameName = trainingManagement.findTraineeByName(name);
                    if (!(traineeWithSameName == null)) {
                        for (Trainee value : traineeWithSameName) {
                            System.out.println("ID: " + value.getId());
                            System.out.println("Name: " + value.getName());
                            System.out.println("Gender: " + value.getGender());
                            System.out.println("Age: " + value.getAge());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter trainee's id: ");
                    id = scanner.nextLine();
                    System.out.println("Enter trainee's name: ");
                    name = scanner.nextLine();
                    System.out.println("Enter trainee's age: ");
                    byte age = scanner.nextByte();
                    scanner.nextLine();
                    System.out.println("Enter trainee's gender: ");
                    String gender = scanner.nextLine();

                    if(!name.isEmpty() && !id.isEmpty() && (gender.equals("Male") || gender.equals("Female")) && (age >= 6)) {
                        trainee.setId(id);
                        trainee.setName(name);
                        trainee.setGender(gender);
                        trainee.setAge(age);
                    } else {
                        System.out.println("Invalid trainee detail");
                        break;
                    }
                    trainingManagement.updateTrainee(id, trainee);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addTrainee() {
        Trainee trainee = traineeForm.getTrainee(); // name, gender, age

        if(trainee == null) {
            System.out.println("Invalid trainee detail");
            return;
        }

        System.out.println("Enter trainee's id: ");
        String id = traineeForm.getId(); // id

        if(!id.isEmpty()) {
            trainee.setId(id);
        } else {
            System.out.println("Trainee's id is empty");
            return;
        }

        listOfTrainee[this.count++] = trainee;
        System.out.println("New trainee has been created.");
    }

    private void displayAllTrainee() {
        if(!isEmpty(this.count)) {
            for(int i = 0; i < this.count; i++) {
                Trainee trainee = listOfTrainee[i];
                System.out.println("ID: " + trainee.getId());
                System.out.println("Name: " + trainee.getName());
                System.out.println("Gender: " + trainee.getGender());
                System.out.println("Age: " + trainee.getAge());
            }
        } else {
            System.out.println("There is no trainee");
        }
    }

    private Trainee findTraineeById(String id) {
        if(!isEmpty(this.count)) {
            for(int i = 0; i < count; i++) {
                Trainee trainee = listOfTrainee[i];

                if(trainee.getId().equals(id)) {
                    return trainee;
                }
            }
            System.out.println("Trainee with id " + id + " not found");
            return null;
        }
        System.out.println("There is no trainee");
        return null;
    }

    private Trainee[] findTraineeByName(String name) {
        byte size = 0;
        Trainee[] temp = new Trainee[100];
        if(!isEmpty(this.count)) {
            for(int i = 0; i < this.count; i++) {
                Trainee trainee = listOfTrainee[i];
                if(trainee.getName().equals(name)) {
                    temp[size] = trainee;
                    size++;
                }
            }
        }
        if(size == 0) {
            System.out.println("List has no trainee with same name.");
            return null;
        } else {
            Trainee[] listOfTraineeWithSameName = new Trainee[size];
            for(int i = 0; i < size; i++) {
                listOfTraineeWithSameName[i] = temp[i];
            }
            return listOfTraineeWithSameName;
        }
    }

    private void updateTrainee(String id, Trainee newTrainee) {
        if(!isEmpty(this.count)) {
            for(int i = 0; i < this.count; i++) {
                if(listOfTrainee[i].getId().equals(id)) {
                    listOfTrainee[i] = newTrainee;
                    System.out.println("Successfully updated trainee with id " + id + ", name " + newTrainee.getName() + ",  gender " + newTrainee.getGender() + ", age " + newTrainee.getAge());
                    return;
                }
            }
        }
        System.out.println("Trainee with id " + id + " not found");
    }
}