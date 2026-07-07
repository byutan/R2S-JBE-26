package session06.training.utils;

import session06.training.entities.Course;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;


public class Validator {
    public Validator() {}

    public static boolean validateCode(String code) {
        return Pattern.matches(Constants.REGEX_CODE, code);
    }

    public static boolean isDuplicatedCode(String code, ArrayList<Course> courses) {
        return courses.stream().anyMatch(course -> code.equals(course.getCode()));
    }

    public static boolean validateStatus(boolean status) {
        return status;
    }

    public static boolean validateFlag(String flag) {
        if(flag == null || flag.trim().isEmpty()) {
            return false;
        }
        return flag.equals(Constants.OPTIONAL)
                || flag.equals(Constants.PREREQUISITE)
                || flag.equals(Constants.NONE);
    }

    public static boolean validateDuration(Short duration) {
        return duration != null && duration > Constants.MIN_DURATION;
    }
}
