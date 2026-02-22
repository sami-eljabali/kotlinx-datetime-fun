package kotlinxdatetimefun.localdate.extensions

import kotlinx.datetime.LocalDate
import kotlinxdatetimefun.isLeapYear

fun LocalDate.isInLeapYear(): Boolean = isLeapYear(year)

fun LocalDate.getMonthBaseZero(): Int = this.month.value - 1

fun LocalDate.getDaysInMonth(): Int = this.month.length(isInLeapYear())