package pl.edu.pwr.zpi.autoasystent.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO Dokumentacja
 *
 * @author Szymon Bartczak
 * @date 2016-05-03
 */
public class DateUtils {

    public static String DATE_PATTERN = "dd.MM.yyyy";
    public static String DATE_AND_TIME_PATTERN = "dd.MM.yyyy HH:mm";
    public static String TIME_PATTERN = "HH:mm";

    /**
     * Konwertuje {@link Date} na pattern podany w parametrze. Można skorzystać z gotowych patternów z klasy {@link DateUtils}.
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * Konwertuje {@link Date} na pattern {@link DateUtils#DATE_PATTERN}.
     */
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        return dateFormat.format(date);
    }

    /**
     * Konwertuje {@link String} z datą wg. podanego patternu.
     * @throws ParseException
     */
    public static Date stringToDate(String dateString, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.parse(dateString);
    }
}
