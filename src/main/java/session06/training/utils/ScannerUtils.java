package session06.training.utils;

import java.util.Scanner;

public final class ScannerUtils {
    private final Scanner scanner;

    public ScannerUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString() {
        String inputString = scanner.nextLine();
        if(inputString == null || inputString.isEmpty()) {
            throw new IllegalArgumentException("Input is empty.");
        }
        return inputString;
    }

    public Short readShort() {
        String inputString = readString();
        return Short.parseShort(inputString);
    }

    public Boolean readBoolean() {
        String inputString = readString();
        if(inputString.equals(Constants.ACTIVE_STATUS)) {
            return true;
        } else if(inputString.equals(Constants.INACTIVE_STATUS)) {
            return false;
        }
        throw new IllegalArgumentException("Invalid status.");
    }
}