package com.rickand.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * The type Utils.
 */
public class Utils {
    /**
     * Format date string.
     *
     * @param airDate the air date
     * @return the string
     */
    public static String formatDate(String airDate){
        SimpleDateFormat in = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
        String formatOut=null;
        try {
            Date fechaDate = in.parse(airDate);
            formatOut = out.format(fechaDate);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        return formatOut;
    }

    /**
     * Convert string to date local date.
     *
     * @param airDate the air date
     * @return the local date
     */
    public static LocalDate ConvertStringToDate(String airDate){
        LocalDate date=null;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(airDate, df);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
