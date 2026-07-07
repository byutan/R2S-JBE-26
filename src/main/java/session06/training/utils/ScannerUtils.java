package session06.training.utils;

import java.util.Scanner;

public final class ScannerUtils {
    private static final Scanner scanner = new Scanner(System.in);
    public ScannerUtils() {}

    public static String readNonEmptyString(String prompt) {
        while (true) {
            if(!prompt.isEmpty()) {
                return prompt;
            } else {
                System.out.print("Invalid value, please try again:");
                prompt = scanner.nextLine();
            }
        }
    }

    public static short readNonNegativeShort(String prompt) {
        while (true) {
            if(!prompt.isEmpty()) {
                short value = Short.parseShort(prompt);
                if(value > Constants.MIN_DURATION) {
                    return value;
                } else {
                    System.out.println("Value cannot be non-negative, please try again:");
                    prompt = scanner.nextLine();
                }
            } else {
                System.out.println("Invalid number,  please try again:");
                prompt = scanner.nextLine();
            }
        }
    }

    public static boolean readBoolean(String prompt) {
        while(true) {
            if(prompt.equalsIgnoreCase("true")) {
                return true;
            } else if(prompt.equalsIgnoreCase("false")) {
                return false;
            } else {
                System.out.println("Invalid value, please try again: ");
                prompt = scanner.nextLine();
            }
        }
    }

    public static int readMenuOption() {
        while(true) {
            System.out.println("1. CREATE COURSE");
            System.out.println("2. SEARCH COURSE BY TYPE");
            System.out.println("3. DISPLAY COURSE BY FLAG");
            System.out.println("4. QUIT");

            System.out.print("ENTER OPTION: ");
            String line = scanner.nextLine();

            try {
                int value = Integer.parseInt(line);
                if(value >= 1 && value <= 4) {
                    return value;
                }
                System.out.println("Value must be from 1 to 4");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }
    }
}
