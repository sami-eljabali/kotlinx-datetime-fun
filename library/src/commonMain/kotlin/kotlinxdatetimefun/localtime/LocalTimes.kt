package kotlinxdatetimefun.localtime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinxdatetimefun.localdatetime.extensions.toLocalTime
import kotlinxdatetimefun.localdatetime.now

fun LocalTime.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalTime = LocalDateTime.now(timeZone).toLocalTime()

val LocalTime.Companion.MIN get() = LocalTime(0, 0)
val LocalTime.Companion.NOON get() = LocalTime(12, 0)
val LocalTime.Companion.MAX get() = LocalTime(23, 59, 59, 999_999_999)