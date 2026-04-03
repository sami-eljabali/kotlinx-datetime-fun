package kotlinxdatetimefun.localdate.extensions

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.plus
import kotlinxdatetimefun.localtime.MAX
import kotlinxdatetimefun.localtime.MIN

fun LocalDate.plusYears(years: Int): LocalDate = this.plus(period = DatePeriod(years = years))

fun LocalDate.minusYears(years: Int): LocalDate = this.minus(period = DatePeriod(years = years))

fun LocalDate.plusMonths(months: Int): LocalDate = this.plus(period = DatePeriod(months = months))

fun LocalDate.minusMonths(months: Int): LocalDate = this.minus(period = DatePeriod(months = months))

fun LocalDate.plusWeeks(weeks: Int): LocalDate = this.plus(period = DatePeriod(days = weeks * 7))

fun LocalDate.minusWeeks(weeks: Int): LocalDate = this.minus(period = DatePeriod(days = weeks * 7))

fun LocalDate.plusDays(days: Int): LocalDate = this.plus(period = DatePeriod(days = days))

fun LocalDate.minusDays(days: Int): LocalDate = this.minus(period = DatePeriod(days = days))

fun LocalDate.getLast(
    dayOfWeek: DayOfWeek,
    countingInThisDay: Boolean = false,
): LocalDate {
    if (countingInThisDay && this.dayOfWeek == dayOfWeek) {
        return this
    }
    var mostRecentDay = this
    if (mostRecentDay.dayOfWeek == dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1)
    }
    while (mostRecentDay.dayOfWeek != dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1)
    }
    return mostRecentDay
}

fun LocalDate.getNext(
    dayOfWeek: DayOfWeek,
    countingInThisDay: Boolean = false,
): LocalDate {
    if (countingInThisDay && this.dayOfWeek == dayOfWeek) {
        return this
    }
    var nextLocalDate = this
    if (nextLocalDate.dayOfWeek == dayOfWeek) {
        nextLocalDate = nextLocalDate.plusDays(1)
    }
    while (nextLocalDate.dayOfWeek != dayOfWeek) {
        nextLocalDate = nextLocalDate.plusDays(1)
    }
    return nextLocalDate
}

fun LocalDate.atStartOfDay(): LocalDateTime = LocalDateTime(this, LocalTime.MIN)

fun LocalDate.atEndOfDay(): LocalDateTime = LocalDateTime(this, LocalTime.MAX)

fun LocalDate.atStartOfMonth(): LocalDate = LocalDate(year, month, 1)

fun LocalDate.atEndOfMonth(): LocalDate = LocalDate(year, month, getDaysInMonth())

fun LocalDate.withYear(year: Int): LocalDate = LocalDate(year, month, day.coerceAtMost(daysInMonth(year, month.number)))

fun LocalDate.withMonth(month: Int): LocalDate = LocalDate(year, month, day.coerceAtMost(daysInMonth(year, month)))

fun LocalDate.withDay(day: Int): LocalDate = LocalDate(year, month, day)

private fun isLeapYear(year: Int): Boolean = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)

private fun daysInMonth(
    year: Int,
    month: Int,
): Int =
    when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> if (isLeapYear(year)) 29 else 28
        else -> throw IllegalStateException("Invalid month number: $month")
    }
