package kotlinxdatetimefun.localdatetime.extensions

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.monthsUntil
import kotlinx.datetime.toInstant
import kotlinx.datetime.periodUntil
import kotlinx.datetime.yearsUntil
import kotlin.time.Duration
import kotlin.time.DurationUnit

// region Day Comparisons
fun LocalDateTime.compareDay(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Int {
    val dayDifference = this.getDayDifference(localDateTimeB, timeZone)
    return when {
        dayDifference > 0 -> -1
        dayDifference < 0 -> 1
        else -> 0
    }
}

fun LocalDateTime.isEqualDay(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Boolean = this.compareDay(localDateTimeB, timeZone) == 0

fun LocalDateTime.isBeforeDay(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Boolean = this.compareDay(localDateTimeB, timeZone) < 0

fun LocalDateTime.isBeforeEqualDay(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Boolean = this.compareDay(localDateTimeB, timeZone) <= 0

fun LocalDateTime.isAfterDay(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Boolean = this.compareDay(localDateTimeB, timeZone) > 0

fun LocalDateTime.isAfterEqualDay(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Boolean = this.compareDay(localDateTimeB, timeZone) >= 0
// endregion

// region Month Comparisons
fun LocalDateTime.compareMonth(localDateTimeB: LocalDateTime): Int =
    when {
        this.isBeforeMonth(localDateTimeB) -> -1
        this.isEqualMonth(localDateTimeB) -> 0
        else -> 1
    }

fun LocalDateTime.isBeforeMonth(localDateTimeB: LocalDateTime): Boolean {
    if (this.year > localDateTimeB.year) return false
    if (this.year < localDateTimeB.year) return true
    return this.month < localDateTimeB.month
}

fun LocalDateTime.isBeforeEqualMonth(localDateTimeB: LocalDateTime): Boolean {
    if (this.year > localDateTimeB.year) return false
    if (this.year < localDateTimeB.year) return true
    return this.month <= localDateTimeB.month
}

fun LocalDateTime.isEqualMonth(localDateTimeB: LocalDateTime): Boolean =
    this.year == localDateTimeB.year && this.month == localDateTimeB.month

fun LocalDateTime.isAfterEqualMonth(localDateTimeB: LocalDateTime): Boolean {
    if (this.year > localDateTimeB.year) return true
    if (this.year < localDateTimeB.year) return false
    return this.month >= localDateTimeB.month
}

fun LocalDateTime.isAfterMonth(localDateTimeB: LocalDateTime): Boolean {
    if (this.year > localDateTimeB.year) return true
    if (this.year < localDateTimeB.year) return false
    return this.month > localDateTimeB.month
}
// endregion

// region Year Comparisons
fun LocalDateTime.compareYear(localDateTimeB: LocalDateTime): Int =
    this.year.compareTo(localDateTimeB.year)

fun LocalDateTime.isBeforeYear(localDateTimeB: LocalDateTime): Boolean =
    this.year < localDateTimeB.year

fun LocalDateTime.isBeforeEqualYear(localDateTimeB: LocalDateTime): Boolean =
    this.year <= localDateTimeB.year

fun LocalDateTime.isEqualYear(localDateTimeB: LocalDateTime): Boolean =
    this.year == localDateTimeB.year

fun LocalDateTime.isAfterEqualYear(localDateTimeB: LocalDateTime): Boolean =
    this.year >= localDateTimeB.year

fun LocalDateTime.isAfterYear(localDateTimeB: LocalDateTime): Boolean =
    this.year > localDateTimeB.year
// endregion

// region Time Comparisons
fun LocalDateTime.compareTime(localDateTimeB: LocalDateTime): Int = this.compareTo(localDateTimeB)

fun LocalDateTime.isEqualTime(localDateTimeB: LocalDateTime): Boolean = this.compareTime(localDateTimeB) == 0

fun LocalDateTime.isBeforeTime(localDateTimeB: LocalDateTime): Boolean = this.compareTime(localDateTimeB) < 0

fun LocalDateTime.isBeforeEqualTime(localDateTimeB: LocalDateTime): Boolean = this.compareTime(localDateTimeB) <= 0

fun LocalDateTime.isAfterTime(localDateTimeB: LocalDateTime): Boolean = this.compareTime(localDateTimeB) > 0

fun LocalDateTime.isAfterEqualTime(localDateTimeB: LocalDateTime): Boolean = this.compareTime(localDateTimeB) >= 0
// endregion

fun LocalDateTime.getPeriodDifference(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): DateTimePeriod =
    this.toInstant(timeZone).periodUntil(localDateTimeB.toInstant(timeZone), timeZone)

fun LocalDateTime.getDurationDifference(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Duration =
    localDateTimeB.toInstant(timeZone) - this.toInstant(timeZone)

fun LocalDateTime.getSecondDifference(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Long =
    this.getDurationDifference(localDateTimeB, timeZone).toLong(DurationUnit.SECONDS)

fun LocalDateTime.getMinuteDifference(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Long =
    this.getDurationDifference(localDateTimeB, timeZone).toLong(DurationUnit.MINUTES)

fun LocalDateTime.getHourDifference(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Long =
    this.getDurationDifference(localDateTimeB, timeZone).toLong(DurationUnit.HOURS)

fun LocalDateTime.getDayDifference(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Int =
    this.toInstant(timeZone).daysUntil(localDateTimeB.toInstant(timeZone), timeZone)

fun LocalDateTime.getMonthDifference(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Int =
    this.toInstant(timeZone).monthsUntil(localDateTimeB.toInstant(timeZone), timeZone)

fun LocalDateTime.getYearDifference(
    localDateTimeB: LocalDateTime,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): Int =
    this.toInstant(timeZone).yearsUntil(localDateTimeB.toInstant(timeZone), timeZone)
