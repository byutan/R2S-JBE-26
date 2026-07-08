package session06.training.main;

import session06.training.entities.Course;
import session06.training.utils.ScannerUtils;
import session06.training.utils.ValidationException;
import session06.training.utils.Validator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseManagement {
    private ArrayList<Course> courseList;

    public CourseManagement() {
        this.courseList = new ArrayList<>();
    }

    public void input(Scanner scanner) throws ValidationException {
        ScannerUtils scannerUtils = new ScannerUtils(scanner);

        String courseName = null;
        boolean isValidName = false;
        do {
            System.out.print("Enter Course Name: ");
            try {
                courseName = scannerUtils.readString();
                isValidName = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Please try again.\n");
            }
        } while (!isValidName);

        String courseCode = null;
        boolean isValidCode = false;
        do {
            System.out.print("Enter Course Code: ");
            try {
                courseCode = scannerUtils.readString();
                if (Validator.validateCode(courseCode) && !Validator.isDuplicatedCode(courseCode, courseList)) {
                    isValidCode = true;
                } else {
                    System.out.println("Invalid or duplicated course code. Please try again.\n");
                }
            } catch (IllegalArgumentException | ValidationException e) {
                System.out.println(e.getMessage() + " Please try again.\n");
            }
        } while (!isValidCode);

        boolean courseStatus = false;
        boolean isValidStatus = false;
        do {
            System.out.print("Enter Course Status (\"active\"/\"inactive\"): ");
            try {
                courseStatus = scannerUtils.readBoolean();
                isValidStatus = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Please try again.\n");
            }
        } while (!isValidStatus);

        short courseDuration = 0;
        boolean isValidDuration = false;
        do {
            System.out.print("Enter Course duration: ");
            try {
                courseDuration = scannerUtils.readShort();
                if (Validator.validateDuration(courseDuration)) {
                    isValidDuration = true;
                } else {
                    System.out.println("Invalid duration value. Please try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Duration must be a number. Please try again.\n");
            } catch (IllegalArgumentException | ValidationException e) {
                System.out.println(e.getMessage() + " Please try again.\n");
            }
        } while (!isValidDuration);

        String courseFlag = null;
        boolean isValidFlag = false;
        do {
            System.out.print("Enter Course flag (\"optional\"/\"prerequisite\"/\"N/A\"): ");
            try {
                courseFlag = scannerUtils.readString();
                if (Validator.validateFlag(courseFlag)) {
                    isValidFlag = true;
                } else {
                    System.out.println("Invalid course flag. Please try again.\n");
                }
            } catch (IllegalArgumentException | ValidationException e) {
                System.out.println(e.getMessage() + " Please try again.\n");
            }
        } while (!isValidFlag);

        Course newCourse = new Course(courseCode, courseName, courseStatus, courseDuration, courseFlag);
        courseList.add(newCourse);
        System.out.println("\nNew Course added to the list successfully.");
    }

    public ArrayList<Course> search(String type, Object data) {
        ArrayList<Course> searchedCourses = new ArrayList<>();
        return switch (type) {
            case "code" -> {
                searchedCourses = courseList
                        .stream()
                        .filter(course -> course.getCode().equals(data))
                        .collect(Collectors.toCollection(ArrayList::new));
                yield searchedCourses;
            }
            case "name" -> {
                searchedCourses = courseList
                        .stream()
                        .filter(course -> course.getName().equals(data))
                        .collect(Collectors.toCollection(ArrayList::new));
                yield searchedCourses;
            }
            case "flag" -> {
                searchedCourses = courseList
                        .stream()
                        .filter(course -> course.getFlag().equals(data))
                        .collect(Collectors.toCollection(ArrayList::new));
                yield searchedCourses;
            }
            case "duration" -> {
                searchedCourses = courseList
                        .stream()
                        .filter(course -> course.getDuration() == Short.parseShort((String) data))
                        .collect(Collectors.toCollection(ArrayList::new));
                yield searchedCourses;
            }
            case "status" -> {
                searchedCourses = courseList
                        .stream()
                        .filter(course -> course.isStatus() == Boolean.parseBoolean((String) data))
                        .collect(Collectors.toCollection(ArrayList::new));
                yield searchedCourses;
            }
            default -> {
                System.out.println("Invalid search type. Please try again.\n");
                yield searchedCourses;
            }
        };
    }

    public void displayAll(String flag) {
        switch (flag) {
            case "optional" -> {
                ArrayList<Course> searchedCourses = search("flag", "optional");
                displaySearchedCourses(searchedCourses);
            }
            case "prerequisite" -> {
                ArrayList<Course> searchedCourses = search("flag", "prerequisite");
                extracted(searchedCourses);
            }
            case "N/A" -> {
                ArrayList<Course> searchedCourses = search("flag", "N/A");
                displaySearchedCourses(searchedCourses);
            }
            default -> {
                System.out.println("Invalid flag. Please try again.\n");
            }
        }
    }

    private static void extracted(ArrayList<Course> searchedCourses) {
        displaySearchedCourses(searchedCourses);
    }

    private static void displaySearchedCourses(ArrayList<Course> searchedCourses) {
        if(searchedCourses.isEmpty()) {
            System.out.println("No course found.");
        } else {
            for(Course course : searchedCourses) {
                System.out.println(course.toString());
            }
        }
    }

    public static void main(String[] args) {
        CourseManagement courseManagement = new CourseManagement();
        Scanner scanner = new Scanner(System.in);

        do {
            try {
                System.out.println("1. Add Course");
                System.out.println("2. Search course by type attribute and its value(\"code\"/\"name\"/\"flag\"/\"status\"/\"duration\")");
                System.out.println("3. Display attribute base on flag value (\"optional\"/\"prerequisite\"/\"N/A\")");
                System.out.println("4. Quit");

                byte option = scanner.nextByte();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        courseManagement.input(scanner);
                        break;
                    case 2:
                        System.out.print("Enter search type: ");
                        String searchType = scanner.nextLine();

                        System.out.print("Enter search value: ");
                        String searchValue = scanner.nextLine();

                        ArrayList<Course> searchedCourses= courseManagement.search(searchType, searchValue);
                        displaySearchedCourses(searchedCourses);
                        break;
                    case 3:
                        System.out.print("Enter flag value: ");
                        String flagValue = scanner.nextLine();

                        courseManagement.displayAll(flagValue);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number from the menu.\n");
                scanner.nextLine();

            } catch (ValidationException | IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (true);
    }
}