package kotlinxdatetimefun.localdatetime.extensions

import kotlinx.datetime.LocalDateTime
import kotlinxdatetimefun.isLeapYear

fun LocalDateTime.isInLeapYear(): Boolean = isLeapYear(this.year)

fun LocalDateTime.isAtStartOfDay(): Boolean = this.isEqualTime(this.atStartOfDay())

fun LocalDateTime.isAtEndOfDay(): Boolean = this.isEqualTime(this.atEndOfDay())

fun LocalDateTime.getMonthBaseZero(): Int = this.monthNumber - 1

fun LocalDateTime.getDaysInMonth(): Int = this.month.length(isInLeapYear())