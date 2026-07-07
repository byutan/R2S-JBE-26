package session06.training.utils;

public final class Constants {
    public Constants() {};

    public static final String REGEX_CODE = "^RA[0-9][0-9][0-9]$";
    public static final byte ACTIVE_STATUS = 1;
    public static final byte INACTIVE_STATUS = 0;

    public static final String OPTIONAL = "optional";
    public static final String PREREQUISITE = "prerequisite";
    public static final String NONE = "N/A";
    public static final byte MIN_DURATION = 0;

    public static final String TYPE_CODE = "code";
    public static final String TYPE_NAME = "name";
    public static final String TYPE_STATUS = "status";
    public static final String TYPE_DURATION = "duration";
    public static final String TYPE_FLAG = "flag";

    public static final int CREATE_COURSE = 1;
    public static final int SEARCH_COURSE = 2;
    public static final int DISPLAY_COURSE = 3;
    public static final int QUIT = 4;
}
