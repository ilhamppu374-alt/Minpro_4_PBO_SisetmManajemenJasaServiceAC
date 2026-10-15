package util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * 
 * 
 */
public class TanggalUtil {

    private static final DateTimeFormatter FORMAT
            = DateTimeFormatter.ofPattern("dd-MM-uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);

    private TanggalUtil() {
    }

    public static boolean valid(String tanggal) {

        try {

            LocalDate.parse(tanggal, FORMAT);
            return true;

        } catch (DateTimeParseException e) {

            return false;
        }
    }

    public static Date keSqlDate(String tanggal) {
        return Date.valueOf(LocalDate.parse(tanggal, FORMAT));
    }

    public static String dariSqlDate(Date date) {
        return date.toLocalDate().format(FORMAT);
    }
}
