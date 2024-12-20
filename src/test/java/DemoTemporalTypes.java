import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DemoTemporalTypes {

    // Temporal types:
    //
    // Oldschool types => represents date, timestamp, time with only 1 type
    // * Date: java 1.0
    // * Calendar (abstract), GregorianCalendar (concrete): java 1.1
    //
    // New types (Java 8): ISO 8601, package java.time
    // * LocalDate, LocalDateTime, LocalTime, ZonedDateTime, ...
    // * Instant
    // * Duration, Period

    @Test
    void demoOldTypes(){
        var date = new Date();
        System.out.println(date);
        var cal = Calendar.getInstance();
        System.out.println(cal);
    }

    @Test
    void demoNewTypes(){
        // Doc temporal text formats:
        // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/format/DateTimeFormatter.html
        var dateFormatFr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var dateFormatText = DateTimeFormatter.ofPattern("eeee dd MMMM y");
        // dates
        List<LocalDate> dates = List.of(
                LocalDate.now(), // date of today
                LocalDate.of(2024, 2, 29),  // date 29 février 2024
                LocalDate.parse("2024-06-12"), // from ISO format '2024-06-12'
                LocalDate.parse("20/11/2023", dateFormatFr)          // from FR format '20/11/2023'
        );
        dates.forEach(
                date -> System.out.println(
                        "ISO: " + date
                        + " ; FR: " + date.format(dateFormatFr)
                        + " ; Text: " + date.format(dateFormatText)
                        + " ; day: " + date.getDayOfMonth()  // alt: day of week, day of year
                        + " ; month: " + date.getMonthValue()  // as int value or object Month
                        + " ; year: " + date.getYear()
                )
        );

        System.out.println();
        var dt = LocalDateTime.now();
        System.out.println("Datetime: " + dt);
        var zdt = ZonedDateTime.now();
        System.out.println("Zoned datetime: " + zdt);
        var t = LocalTime.now();
        System.out.println("Time: " + t);
    }

}
