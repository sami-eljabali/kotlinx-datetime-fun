package kotlinxdatetimefun.localtime.extensions

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinxdatetimefun.localdatetime.extensions.getPeriodDifference
import kotlinxdatetimefun.localdatetime.extensions.withLocalTime
import kotlinxdatetimefun.localdatetime.of

fun LocalTime.periodUntil(localTimeB: LocalTime): DateTimePeriod {
    val localDateTimeA = LocalDateTime.of(2025, 1, 1).withLocalTime(this)
    val localDateTimeB = LocalDateTime.of(2025, 1, 1).withLocalTime(localTimeB)
    return localDateTimeA.getPeriodDifference(localDateTimeB)
}

fun LocalTime.getNanoSecondDifference(localTimeB: LocalTime): Long {
    val period = this.periodUntil(localTimeB)
    val seconds = period.hours * 3_600 + period.minutes * 60 + period.seconds
    return seconds.toLong() * 1_000_000_000 + period.nanoseconds.toLong()
}

fun LocalTime.getMilliSecondDifference(localTimeB: LocalTime): Long {
    val period = this.periodUntil(localTimeB)
    val seconds = period.hours * 3_600 + period.minutes * 60 + period.seconds
    // Divide nanoseconds by 1,000,000 to convert to milliseconds.
    return seconds.toLong() * 1_000 + (period.nanoseconds / 1_000_000L)
}

fun LocalTime.getSecondDifference(localTimeB: LocalTime): Int {
    val period = this.periodUntil(localTimeB)
    return period.hours * 3_600 + period.minutes * 60 + period.seconds
}

fun LocalTime.getMinuteDifference(localTimeB: LocalTime): Int {
    val period = this.periodUntil(localTimeB)
    return period.hours * 60 + period.minutes
}

fun LocalTime.getHourDifference(localTimeB: LocalTime): Int =
    this.periodUntil(localTimeB).hours