package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeManager {
    DateTime startTime, endTime;
    DateTimeFormatter gameTimeFormat = DateTimeFormat.forPattern("ss");
    DateTimeFormatter startTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    private static TimeManager instance = null;

    public TimeManager() {
    }

    public void setStartTime() {
        startTime = new DateTime();
    }

    public void setEndTime() {
        endTime = new DateTime();
    }

    public String getDate() {
        return startTimeFormat.print(startTime.getMillis());
    }

    public String gameTime() {
        return gameTimeFormat.print(
                endTime.minusHours(startTime.getHourOfDay())
                        .minusMinutes(startTime.getMinuteOfHour())
                        .minusSeconds(startTime.getSecondOfMinute())
        );
    }

    public static TimeManager getInstance() {
        if (instance == null) {
            instance = new TimeManager();
        }

        return instance;
    }
}