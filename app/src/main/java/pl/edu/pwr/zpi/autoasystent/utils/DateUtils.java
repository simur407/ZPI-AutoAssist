package pl.edu.pwr.zpi.autoasystent.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa zapewniająca konwersję dat na sformatowane Stringi, oraz Stringi na obiekty {@link Date}.
 *
 * @author Szymon Bartczak
 * @date 2016-05-03
 */
public class DateUtils {

    public static String DATE_FORMAT_DEF = "dd.MM.yyyy";
    public static String DATE_AND_TIME_FORMAT_DEF = "dd.MM.yyyy HH:mm";
    public static String TIME_FORMAT = "HH:mm";
    public static String YEAR_FORMAT = "yyyy";

    /**
     * Konwertuje {@link Date} na format podany w parametrze. Można skorzystać z gotowych formatów z klasy {@link DateUtils}.
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * Konwertuje {@link Date} na format {@value DateUtils#DATE_FORMAT_DEF}.
     */
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_DEF);
        return dateFormat.format(date);
    }

    /**
     * Konwertuje {@link String} z datą wg. podanego patternu.
     *
     * @throws ParseException
     */
    public static Date stringToDate(String dateString, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.parse(dateString);
    }

    /**
     * Konwertuje format HHmm zapisany jako int na String {@value TIME_FORMAT}.
     */
    public static String timeToString(int time) {
        return String.format("%d:%02d", time / 100, time % 100);
    }
}
