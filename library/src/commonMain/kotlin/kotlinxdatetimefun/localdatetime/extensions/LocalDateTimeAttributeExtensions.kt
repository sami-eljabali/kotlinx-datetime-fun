package kotlinxdatetimefun.localdatetime.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number
import kotlinxdatetimefun.isLeapYear

fun LocalDateTime.isInLeapYear(): Boolean = isLeapYear(this.year)

fun LocalDateTime.isAtStartOfDay(): Boolean = this.isEqualTime(this.atStartOfDay())

fun LocalDateTime.isAtEndOfDay(): Boolean = this.isEqualTime(this.atEndOfDay())

fun LocalDateTime.getMonthBaseZero(): Int = month.number - 1

fun LocalDateTime.getDaysInMonth(): Int = when (month.number) {
    1, 3, 5, 7, 8, 10, 12 -> 31
    4, 6, 9, 11 -> 30
    2 -> if (isInLeapYear()) 29 else 28
    else -> throw IllegalStateException("Invalid month number: ${month.number}")
}
