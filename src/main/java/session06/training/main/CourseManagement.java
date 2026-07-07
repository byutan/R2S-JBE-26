package session06.training.main;

import session06.training.entities.Course;
import session06.training.utils.Constants;
import session06.training.utils.ScannerUtils;
import session06.training.utils.Validator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseManagement {
    private final ArrayList<Course> courses;

    public CourseManagement() {
        courses = new ArrayList<>();
    }

    private void input(Scanner scanner) {
        System.out.print("Enter courses code: ");
        String code = ScannerUtils.readNonEmptyString(scanner.nextLine());

        System.out.print("Enter courses name: ");
        String name = ScannerUtils.readNonEmptyString(scanner.nextLine());

        System.out.print("Enter courses status (true/false): ");
        boolean status = ScannerUtils.readBoolean(scanner.nextLine());

        System.out.print("Enter courses duration: ");
        short duration = ScannerUtils.readNonNegativeShort(scanner.nextLine());

        System.out.print("Enter courses flag: ");
        String flag = ScannerUtils.readNonEmptyString(scanner.nextLine());

        boolean isValidCourse = Validator.validateCode(code)
                && Validator.validateStatus(status)
                && Validator.validateDuration(duration)
                && Validator.validateFlag(flag);

        if (isValidCourse){
            Course newCourse = new Course(code, name, status, duration, flag);
                if(!Validator.isDuplicatedCode(newCourse.getCode(), courses)) {
                courses.add(newCourse);
                System.out.println("Course added successfully");
                return;
            }
        }
        System.out.println("Add courses failed");
    }

    private ArrayList<Course> search(String type, Object data) throws IllegalArgumentException{
        ArrayList<Course> results = new ArrayList<>();
        if(type == null || data == null) {
            throw new IllegalArgumentException("Type and data not found");
        }
        for(Course course : courses) {
            switch (type.toLowerCase()) {
                case Constants.TYPE_CODE:
                    if(data instanceof String code) {
                        if(code.equalsIgnoreCase(course.getCode())) {
                            results.add(course);
                        }
                    }
                    break;
                case Constants.TYPE_NAME:
                    if(data instanceof String name) {
                        if(name.equalsIgnoreCase(course.getName())) {
                            results.add(course);
                        }
                    }
                    break;
                case Constants.TYPE_STATUS:
                    if(data instanceof Boolean status) {
                        if(status.equals(course.getStatus())) {
                            results.add(course);
                        }
                    }
                    break;

                case Constants.TYPE_DURATION:
                    if(data instanceof Short duration) {
                        if(duration.equals(course.getDuration())) {
                            results.add(course);
                        }
                    }
                    break;
                case Constants.TYPE_FLAG:
                    if(data instanceof String flag) {
                        if(flag.equalsIgnoreCase(course.getFlag())) {
                            results.add(course);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if(results.isEmpty()) {
            System.out.println("Course not found");
        }
        return results;
    }
    private void displayAll(String flag) {
        ArrayList<Course> filterCourse = courses.stream()
                .filter(course -> course.getFlag().equalsIgnoreCase(flag))
                .collect(Collectors.toCollection(ArrayList::new));
        for(Course course : filterCourse) {
            System.out.println(course.toString());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseManagement courseManagement = new CourseManagement();
        do {
            int option = ScannerUtils.readMenuOption();
                switch (option) {
                    case Constants.CREATE_COURSE:
                        courseManagement.input(scanner);
                        break;
                    case Constants.SEARCH_COURSE:
                        System.out.print("Enter search type: ");
                        String type = ScannerUtils.readNonEmptyString(scanner.nextLine());
                        System.out.print("Enter search data: ");
                        String data = ScannerUtils.readNonEmptyString(scanner.nextLine());
                        ArrayList<Course> searched_course = courseManagement.search(type, data);
                        for(Course course : searched_course) {
                            System.out.println(course.toString());
                        }
                        break;
                    case Constants.DISPLAY_COURSE:
                        courseManagement.displayAll(ScannerUtils.readNonEmptyString(scanner.nextLine()));
                        break;
                    case Constants.QUIT:
                        return;
                    default:
                        break;
                }
        } while (true);
    }
}
