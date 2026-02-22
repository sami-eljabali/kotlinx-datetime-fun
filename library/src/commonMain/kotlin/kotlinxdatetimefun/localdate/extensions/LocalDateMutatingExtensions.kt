package kotlinxdatetimefun.localdate.extensions

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.number
import kotlinxdatetimefun.localtime.MAX
import kotlinxdatetimefun.localtime.MIN

fun LocalDate.minusDays(days: Int): LocalDate = plusDays(-days)
fun LocalDate.plusDays(days: Int): LocalDate = fromEpochDay(toEpochDay(this) + days)

fun LocalDate.minusMonths(months: Int): LocalDate = plusMonths(-months)
fun LocalDate.plusMonths(months: Int): LocalDate {
    val monthIndex = this.year * 12 + (month.number - 1)
    val targetMonthIndex = monthIndex + months
    val targetYear = floorDiv(targetMonthIndex, 12)
    val targetMonth = floorMod(targetMonthIndex, 12) + 1
    val targetDay = day.coerceAtMost(daysInMonth(targetYear, targetMonth))
    return LocalDate(targetYear, targetMonth, targetDay)
}

fun LocalDate.minusYears(years: Int): LocalDate = plusYears(-years)
fun LocalDate.plusYears(years: Int): LocalDate = plusMonths(years * 12)

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

private fun isLeapYear(year: Int): Boolean = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)

private fun daysInMonth(year: Int, month: Int): Int = when (month) {
    1, 3, 5, 7, 8, 10, 12 -> 31
    4, 6, 9, 11 -> 30
    2 -> if (isLeapYear(year)) 29 else 28
    else -> throw IllegalStateException("Invalid month number: $month")
}

private fun floorDiv(a: Int, b: Int): Int {
    val q = a / b
    val r = a % b
    return if (r != 0 && ((r > 0) != (b > 0))) q - 1 else q
}

private fun floorMod(a: Int, b: Int): Int = a - floorDiv(a, b) * b

private fun toEpochDay(date: LocalDate): Int {
    var y = date.year
    val m = date.month.number
    val d = date.day
    y -= if (m <= 2) 1 else 0
    val era = floorDiv(y, 400)
    val yoe = y - era * 400
    val mp = m + if (m > 2) -3 else 9
    val doy = (153 * mp + 2) / 5 + d - 1
    val doe = yoe * 365 + yoe / 4 - yoe / 100 + doy
    return era * 146097 + doe - 719468
}

private fun fromEpochDay(epochDay: Int): LocalDate {
    val z = epochDay + 719468
    val era = floorDiv(z, 146097)
    val doe = z - era * 146097
    val yoe = (doe - doe / 1460 + doe / 36524 - doe / 146096) / 365
    var y = yoe + era * 400
    val doy = doe - (365 * yoe + yoe / 4 - yoe / 100)
    val mp = (5 * doy + 2) / 153
    val d = doy - (153 * mp + 2) / 5 + 1
    val m = mp + if (mp < 10) 3 else -9
    y += if (m <= 2) 1 else 0
    return LocalDate(y, m, d)
}
