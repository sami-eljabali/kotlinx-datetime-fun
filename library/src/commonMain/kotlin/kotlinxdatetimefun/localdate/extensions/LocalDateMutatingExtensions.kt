package kotlinxdatetimefun.localdate.extensions

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.plus
import kotlinxdatetimefun.localtime.MAX
import kotlinxdatetimefun.localtime.MIN

fun LocalDate.minusDays(days: Int): LocalDate = this.plus(days * -1, DateTimeUnit.DAY)
fun LocalDate.plusDays(days: Int): LocalDate = this.plus(days, DateTimeUnit.DAY)

fun LocalDate.minusMonths(months: Int): LocalDate = this.plus(months * -1, DateTimeUnit.MONTH)
fun LocalDate.plusMonths(months: Int): LocalDate = this.plus(months, DateTimeUnit.MONTH)

fun LocalDate.minusYears(years: Int): LocalDate = this.plus(years * -1, DateTimeUnit.YEAR)
fun LocalDate.plusYears(years: Int): LocalDate = this.plus(years, DateTimeUnit.YEAR)

fun LocalDate.getLast(dayOfWeek: DayOfWeek, countingInThisDay: Boolean = false): LocalDate {
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

fun LocalDate.getNext(dayOfWeek: DayOfWeek, countingInThisDay: Boolean = false): LocalDate {
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
