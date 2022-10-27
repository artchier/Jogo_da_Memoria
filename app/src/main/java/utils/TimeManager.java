package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeManager {
    DateTime inicio, fim;
    DateTimeFormatter duracaoFormato = DateTimeFormat.forPattern("ss");
    DateTimeFormatter inicioFormato = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    private static TimeManager instance = null;

    public TimeManager() {
    }

    public void setInicio() {
        inicio = new DateTime();
    }

    public void setFim() {
        fim = new DateTime();
    }

    public String getDate() {
        return inicioFormato.print(inicio.getMillis());
    }

    public String duracaoPartida() {
        return duracaoFormato.print(
                fim.minusHours(inicio.getHourOfDay())
                        .minusMinutes(inicio.getMinuteOfHour())
                        .minusSeconds(inicio.getSecondOfMinute())
        );
    }

    public static TimeManager getInstance() {
        if (instance == null) {
            instance = new TimeManager();
        }

        return instance;
    }
}