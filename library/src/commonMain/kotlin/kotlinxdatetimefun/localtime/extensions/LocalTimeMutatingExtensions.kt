package kotlinxdatetimefun.localtime.extensions

import kotlinx.datetime.LocalTime

private const val NANOS_PER_DAY = 86_400_000_000_000L

fun LocalTime.plusHours(hours: Int): LocalTime =
    fromWrappedNanos(toNanosecondOfDay() + hours.toLong() * 3_600_000_000_000L)

fun LocalTime.minusHours(hours: Int): LocalTime = plusHours(-hours)

fun LocalTime.plusMinutes(minutes: Int): LocalTime =
    fromWrappedNanos(toNanosecondOfDay() + minutes.toLong() * 60_000_000_000L)

fun LocalTime.minusMinutes(minutes: Int): LocalTime = plusMinutes(-minutes)

fun LocalTime.plusSeconds(seconds: Int): LocalTime =
    fromWrappedNanos(toNanosecondOfDay() + seconds.toLong() * 1_000_000_000L)

fun LocalTime.minusSeconds(seconds: Int): LocalTime = plusSeconds(-seconds)

fun LocalTime.plusNanoseconds(nanoseconds: Long): LocalTime =
    fromWrappedNanos(toNanosecondOfDay() + nanoseconds)

fun LocalTime.minusNanoseconds(nanoseconds: Long): LocalTime = plusNanoseconds(-nanoseconds)

fun LocalTime.withHour(hour: Int): LocalTime = LocalTime(hour, minute, second, nanosecond)

fun LocalTime.withMinute(minute: Int): LocalTime = LocalTime(hour, minute, second, nanosecond)

fun LocalTime.withSecond(second: Int): LocalTime = LocalTime(hour, minute, second, nanosecond)

fun LocalTime.withNanosecond(nanosecond: Int): LocalTime = LocalTime(hour, minute, second, nanosecond)

private fun fromWrappedNanos(nanos: Long): LocalTime {
    val wrapped = ((nanos % NANOS_PER_DAY) + NANOS_PER_DAY) % NANOS_PER_DAY
    return LocalTime.fromNanosecondOfDay(wrapped)
}
