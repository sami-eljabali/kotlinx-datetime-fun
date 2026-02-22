package kotlinxdatetimefun.localdatetime.extensions

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinxdatetimefun.localdate.extensions.plusDays
import kotlinxdatetimefun.localdate.extensions.plusMonths
import kotlinxdatetimefun.localdate.extensions.plusYears
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

fun LocalDateTime.toLocalDate(): LocalDate = LocalDate(
    year = this.year,
    month = month,
    day = day
)

fun LocalDateTime.toLocalTime(): LocalTime = LocalTime(
    hour = this.hour,
    minute = this.minute,
    second = this.second,
    nanosecond = this.nanosecond
)

fun LocalDateTime.atStartOfDay(): LocalDateTime = LocalDateTime(
    year = this.year,
    month = month,
    day = day,
    hour = 0,
    minute = 0,
    second = 0,
    nanosecond = 0
)

fun LocalDateTime.atEndOfDay(): LocalDateTime = LocalDateTime(
    year = this.year,
    month = month,
    day = day,
    hour = 23,
    minute = 59,
    second = 59,
    nanosecond = 999_999_999
)

fun LocalDateTime.withLocalTime(localTime: LocalTime): LocalDateTime = LocalDateTime(
    year = this.year,
    month = month,
    day = day,
    hour = localTime.hour,
    minute = localTime.minute,
    second = localTime.second,
    nanosecond = localTime.nanosecond
)

fun LocalDateTime.minusYears(years: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    LocalDateTime(this.toLocalDate().plusYears(-years), this.toLocalTime())
fun LocalDateTime.plusYears(years: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    LocalDateTime(this.toLocalDate().plusYears(years), this.toLocalTime())

fun LocalDateTime.minusMonths(months: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    LocalDateTime(this.toLocalDate().plusMonths(-months), this.toLocalTime())
fun LocalDateTime.plusMonths(months: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    LocalDateTime(this.toLocalDate().plusMonths(months), this.toLocalTime())

fun LocalDateTime.minusDays(days: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    LocalDateTime(this.toLocalDate().plusDays(-days), this.toLocalTime())
fun LocalDateTime.plusDays(days: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    LocalDateTime(this.toLocalDate().plusDays(days), this.toLocalTime())

fun LocalDateTime.minusHours(hours: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus((-hours).hours)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusHours(hours: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(hours.hours)
        .toLocalDateTime(timeZone)

fun LocalDateTime.minusMinutes(minutes: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus((-minutes).minutes)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusMinutes(minutes: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(minutes.minutes)
        .toLocalDateTime(timeZone)

fun LocalDateTime.minusSeconds(seconds: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus((-seconds).seconds)
        .toLocalDateTime(timeZone)
fun LocalDateTime.plusSeconds(seconds: Int, timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime =
    this.toInstant(timeZone)
        .plus(seconds.seconds)
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
