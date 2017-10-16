package edu.mum.se.poseidon.core.configs;

import java.time.LocalDate;

public class Helper {

    public static LocalDate convertStringToDate(String date) {
        if (date == null) {
            return null;
        }
        return LocalDate.parse(date);
    }

    public static String convertDateToString(LocalDate date) {
        if (date == null) {
            return "";
        }
        return date.toString();
    }
}
