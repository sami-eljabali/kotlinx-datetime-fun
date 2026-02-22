package kotlinxdatetimefun.localdatetime

import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinxdatetimefun.localdatetime.extensions.atStartOfDay
import kotlinxdatetimefun.localdatetime.extensions.getLast
import kotlinxdatetimefun.localdatetime.extensions.getNext
import kotlinxdatetimefun.localdatetime.extensions.minusDays
import kotlinxdatetimefun.localdatetime.extensions.plusDays

fun LocalDateTime.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = Clock.System.now().toLocalDateTime(timeZone)
fun LocalDateTime.Companion.today(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = now(timeZone).atStartOfDay()
fun LocalDateTime.Companion.yesterday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).minusDays(1, timeZone)
fun LocalDateTime.Companion.tomorrow(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).plusDays(1, timeZone)

fun LocalDateTime.Companion.lastMonday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getLast(DayOfWeek.MONDAY, false, timeZone)
fun LocalDateTime.Companion.lastTuesday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getLast(DayOfWeek.TUESDAY, false, timeZone)
fun LocalDateTime.Companion.lastWednesday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getLast(DayOfWeek.WEDNESDAY, false, timeZone)
fun LocalDateTime.Companion.lastThursday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getLast(DayOfWeek.THURSDAY, false, timeZone)
fun LocalDateTime.Companion.lastFriday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getLast(DayOfWeek.FRIDAY, false, timeZone)
fun LocalDateTime.Companion.lastSaturday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getLast(DayOfWeek.SATURDAY, false, timeZone)
fun LocalDateTime.Companion.lastSunday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getLast(DayOfWeek.SUNDAY, false, timeZone)

fun LocalDateTime.Companion.nextMonday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getNext(DayOfWeek.MONDAY, false, timeZone)
fun LocalDateTime.Companion.nextTuesday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getNext(DayOfWeek.TUESDAY, false, timeZone)
fun LocalDateTime.Companion.nextWednesday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getNext(DayOfWeek.WEDNESDAY, false, timeZone)
fun LocalDateTime.Companion.nextThursday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getNext(DayOfWeek.THURSDAY, false, timeZone)
fun LocalDateTime.Companion.nextFriday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getNext(DayOfWeek.FRIDAY, false, timeZone)
fun LocalDateTime.Companion.nextSaturday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getNext(DayOfWeek.SATURDAY, false, timeZone)
fun LocalDateTime.Companion.nextSunday(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = today(timeZone).getNext(DayOfWeek.SUNDAY, false, timeZone)

fun LocalDateTime.Companion.startOfYear(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = LocalDateTime.of(today(timeZone).year, 1, 1)
fun LocalDateTime.Companion.endOfYear(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime = LocalDateTime.of(today(timeZone).year, 12, 31)