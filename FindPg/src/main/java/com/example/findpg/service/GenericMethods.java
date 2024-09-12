package com.example.findpg.service;

import java.util.*;
import java.util.regex.Pattern;

public class GenericMethods {
    private static final HashMap<String, String> regexCollection = new HashMap<>();

    static {
        regexCollection.put(
                "NumberWithoutZero", "^(?!0*(\\.0+)?$)([1-9]\\d{0,5}(\\.\\d{1,6})?|0\\.\\d{1,6})$");
        regexCollection.put("NumberWithZero", "^(0|[1-9]\\d{0,5})(\\.\\d{1,6})?$");
        regexCollection.put("StartsWithAlphaNumeric", "^[a-zA-Z0-9].*");
        regexCollection.put("NumberOnly", "\\d+");
        regexCollection.put("AlphaNumeric", "^[a-zA-Z0-9]+$");
        regexCollection.put(
                "Email",
                "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9]))+$");
        regexCollection.put("NumberOnlyIncludingNegative", "^-?\\d+$");
        regexCollection.put("DoesNotStartWithWhiteSpaces", "^[^\\s].*");
        regexCollection.put("AlphaNumericWithUnderScore", "^[a-zA-Z0-9_]+$");
    }

    public static boolean isNullOrEmpty(String str, boolean alsoCheckForNullString) {
        if (alsoCheckForNullString) {
            return str == null || str.trim().isEmpty() || "null".equalsIgnoreCase(str);
        } else {
            return str == null || str.trim().isEmpty();
        }
    }

    public static Boolean regexChecker(String valueToCheck, String[] regexNames, boolean allTrue) {
        if (allTrue) {
            for (String regexName : regexNames) {
                if (!Pattern.compile(regexCollection.get(regexName))
                        .matcher(valueToCheck)
                        .matches()) {
                    return false;
                }
            }
            return true;
        } else {
            for (String regexName : regexNames) {
                if (Pattern.compile(regexCollection.get(regexName))
                        .matcher(valueToCheck)
                        .matches()) {
                    return true;
                }
            }
            return false;
        }
    }
}
