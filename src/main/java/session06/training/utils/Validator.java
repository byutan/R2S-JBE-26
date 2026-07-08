package session06.training.utils;

import session06.training.entities.Course;

import java.util.ArrayList;

public final class Validator {
    public static boolean validateCode(String code) throws ValidationException {
        if(!code.matches(Constants.REGEX)) {
            throw new ValidationException("Invalid code.");
        }
        return true;
    }

    public static boolean isDuplicatedCode(String code, ArrayList<Course> courseList) throws ValidationException {
        for (Course course : courseList) {
            if (course.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateStatus(boolean status) throws ValidationException {
        return status;
    }

    public static boolean validateDuration(short duration) throws ValidationException {
        if(duration < Constants.MIN) {
            throw new ValidationException("Invalid duration.");
        }
        return true;
    }

    public static boolean validateFlag(String flag) throws ValidationException {
        if(!flag.equals(Constants.OPTIONAL_FLAG) &&
        !flag.equals(Constants.PREREQUISITE_FLAG) &&
        !flag.equals(Constants.NONE_FLAG)) {
            throw new ValidationException("Invalid flag.");
        }
        return true;
    }
}