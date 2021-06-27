package savexport;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.ReadableInstant;
import org.joda.time.Seconds;

public class Tempo {
    public static String result(DateTime paramDateTime1, DateTime paramDateTime2) {
        Hours hours = Hours.hoursBetween((ReadableInstant) paramDateTime1, (ReadableInstant) paramDateTime2);
        Minutes minutes = Minutes.minutesBetween((ReadableInstant) paramDateTime1, (ReadableInstant) paramDateTime2);
        Seconds seconds = Seconds.secondsBetween((ReadableInstant) paramDateTime1, (ReadableInstant) paramDateTime2);
        int i = hours.getHours();
        int j = minutes.getMinutes();
        int k = seconds.getSeconds();
        return (new SimpleDateFormat("HH:mm:ss")).format(new Date(0, 0, 0, i, j, k));
    }
}