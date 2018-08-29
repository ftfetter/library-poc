package com.github.ftfetter.studies.library.user.util;

public class StringUtils {

    public static boolean isBlank(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String notBlankOrElse(String str, String defaultValue) {
        if (isBlank(str)) {
            return defaultValue;
        }
        return str;
    }
}
