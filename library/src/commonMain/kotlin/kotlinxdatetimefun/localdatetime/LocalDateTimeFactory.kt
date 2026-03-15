package kotlinxdatetimefun.localdatetime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun LocalDateTime.Companion.of(
    year: Int,
    month: Int,
    day: Int,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
    nanosecond: Int = 0
): LocalDateTime = LocalDateTime(
    year = year,
    month = Month(month),
    day = day,
    hour = hour,
    minute = minute,
    second = second,
    nanosecond = nanosecond
)

fun LocalDateTime.Companion.of(
    epochMilliseconds: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDateTime =
    Instant.fromEpochMilliseconds(epochMilliseconds)
        .toLocalDateTime(timeZone)