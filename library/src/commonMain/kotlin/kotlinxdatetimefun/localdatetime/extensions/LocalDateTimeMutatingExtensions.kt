package kotlinxdatetimefun.localdatetime.extensions

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun LocalDateTime.toLocalDate(): LocalDate = LocalDate(
    year = this.year,
    monthNumber = this.monthNumber,
    dayOfMonth = this.dayOfMonth
)

fun LocalDateTime.toLocalTime(): LocalTime = LocalTime(
    hour = this.hour,
    minute = this.minute,
    second = this.second,
    nanosecond = this.nanosecond
)

fun LocalDateTime.atStartOfDay(): LocalDateTime = LocalDateTime(
    year = this.year,
    monthNumber = this.monthNumber,
    dayOfMonth = this.dayOfMonth,
    hour = 0,
    minute = 0,
    second = 0,
    nanosecond = 0
)

fun LocalDateTime.atEndOfDay(): LocalDateTime = LocalDateTime(
    year = this.year,
    monthNumber = this.monthNumber,
    dayOfMonth = this.dayOfMonth,
    hour = 23,
    minute = 59,
    second = 59,
    nanosecond = 999_999_999
)

fun LocalDateTime.withLocalTime(localTime: LocalTime): LocalDateTime = LocalDateTime(
    year = this.year,
    monthNumber = this.monthNumber,
    dayOfMonth = this.dayOfMonth,
    hour = localTime.hour,
    minute = localTime.minute,
    second = localTime.second,
    nanosecond = localTime.nanosecond
)

fun LocalDateTime.minusYears(years: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(years * -1, DateTimeUnit.YEAR, timeZone)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusYears(years: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(years, DateTimeUnit.YEAR, timeZone)
        .toLocalDateTime(timeZone)

fun LocalDateTime.minusMonths(months: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(months * -1, DateTimeUnit.MONTH, timeZone)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusMonths(months: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(months, DateTimeUnit.MONTH, timeZone)
        .toLocalDateTime(timeZone)

fun LocalDateTime.minusDays(days: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(days * -1, DateTimeUnit.DAY, timeZone)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusDays(days: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(days, DateTimeUnit.DAY, timeZone)
        .toLocalDateTime(timeZone)

fun LocalDateTime.minusHours(hours: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(hours * -1, DateTimeUnit.HOUR, timeZone)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusHours(hours: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(hours, DateTimeUnit.HOUR, timeZone)
        .toLocalDateTime(timeZone)

fun LocalDateTime.minusMinutes(minutes: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(minutes * -1, DateTimeUnit.MINUTE, timeZone)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusMinutes(minutes: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(minutes, DateTimeUnit.MINUTE, timeZone)
        .toLocalDateTime(timeZone)

fun LocalDateTime.minusSeconds(seconds: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(seconds * -1, DateTimeUnit.SECOND, timeZone)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusSeconds(seconds: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(seconds, DateTimeUnit.SECOND, timeZone)
        .toLocalDateTime(timeZone)

fun LocalDateTime.getLast(
    dayOfWeek: DayOfWeek,
    countingInThisDay: Boolean = false,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDateTime {
    if (countingInThisDay && this.dayOfWeek == dayOfWeek) {
        return this
    }
    var mostRecentDay = this
    if (mostRecentDay.dayOfWeek == dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1, timeZone)
    }
    while (mostRecentDay.dayOfWeek != dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1, timeZone)
    }
    return mostRecentDay
}

fun LocalDateTime.getNext(
    dayOfWeek: DayOfWeek,
    countingInThisDay: Boolean = false,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDateTime {
    if (countingInThisDay && this.dayOfWeek == dayOfWeek) {
        return this
    }
    var nextLocalDate = this
    if (nextLocalDate.dayOfWeek == dayOfWeek) {
        nextLocalDate = nextLocalDate.plusDays(1, timeZone)
    }
    while (nextLocalDate.dayOfWeek != dayOfWeek) {
        nextLocalDate = nextLocalDate.plusDays(1, timeZone)
    }
    return nextLocalDate
}