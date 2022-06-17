package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeManager {
    DateTime inicio, fim;
    DateTimeFormatter horaFormato = DateTimeFormat.forPattern("HH:mm:ss.SSS");
    DateTimeFormatter dataFormato = DateTimeFormat.forPattern("dd/MM/yyyy");

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
        return dataFormato.print(inicio.getMillis());
    }

    public String duracaoPartida() {
        return horaFormato.print(
                fim.minusHours(inicio.getHourOfDay())
                        .minusMinutes(inicio.getMinuteOfHour())
                        .minusSeconds(inicio.getSecondOfMinute())
                        .minusMillis(inicio.getMillisOfSecond())
        );
    }

    public static TimeManager getInstance() {
        if (instance == null) {
            instance = new TimeManager();
        }

        return instance;
    }
}