package kotlinxdatetimefun.localdate.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.number
import kotlinxdatetimefun.isLeapYear

fun LocalDate.isInLeapYear(): Boolean = isLeapYear(year)

fun LocalDate.getMonthBaseZero(): Int = month.number - 1

fun LocalDate.getDaysInMonth(): Int = when (month.number) {
    1, 3, 5, 7, 8, 10, 12 -> 31
    4, 6, 9, 11 -> 30
    2 -> if (isInLeapYear()) 29 else 28
    else -> throw IllegalStateException("Invalid month number: ${month.number}")
}
