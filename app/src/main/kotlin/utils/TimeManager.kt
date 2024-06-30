package utils

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat.forPattern
import org.joda.time.format.DateTimeFormatter

object TimeManager {
    lateinit var startTime: DateTime
    lateinit var endTime: DateTime

    val gameTimeFormat: DateTimeFormatter = forPattern("ss")
    val startTimeFormat: DateTimeFormatter = forPattern("yyyy-MM-dd HH:mm:ss")

    val date: String
        get() = startTimeFormat.print(startTime.millis)

    fun gameTime(): String = gameTimeFormat.print(
            endTime.minusHours(startTime.hourOfDay)
                    .minusMinutes(startTime.minuteOfHour)
                    .minusSeconds(startTime.secondOfMinute)
    )
}