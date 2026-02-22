package kotlinxdatetimefun.localdatetime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month

fun LocalDateTime.Companion.of(
    year: Int,
    month: Int,
    dayOfMonth: Int,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
    nanosecond: Int = 0
): LocalDateTime = LocalDateTime(
    year = year,
    month = Month(month),
    dayOfMonth = dayOfMonth,
    hour = hour,
    minute = minute,
    second = second,
    nanosecond = nanosecond
)
