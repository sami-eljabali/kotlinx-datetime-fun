package kotlinxdatetimefun.localtime.extensions

import kotlinx.datetime.LocalTime

private const val NANOS_PER_DAY = 86_400_000_000_000L

/**
 * Returns a copy of this LocalTime with the given number of hours added, wrapping around midnight.
 */
fun LocalTime.plusHours(hours: Int): LocalTime =
    fromWrappedNanos(toNanosecondOfDay() + hours.toLong() * 3_600_000_000_000L)

fun LocalTime.minusHours(hours: Int): LocalTime = plusHours(-hours)

/**
 * Returns a copy of this LocalTime with the given number of minutes added, wrapping around midnight.
 */
fun LocalTime.plusMinutes(minutes: Int): LocalTime =
    fromWrappedNanos(toNanosecondOfDay() + minutes.toLong() * 60_000_000_000L)

fun LocalTime.minusMinutes(minutes: Int): LocalTime = plusMinutes(-minutes)

/**
 * Returns a copy of this LocalTime with the given number of seconds added, wrapping around midnight.
 */
fun LocalTime.plusSeconds(seconds: Int): LocalTime =
    fromWrappedNanos(toNanosecondOfDay() + seconds.toLong() * 1_000_000_000L)

fun LocalTime.minusSeconds(seconds: Int): LocalTime = plusSeconds(-seconds)

/**
 * Returns a copy of this LocalTime with the given number of nanoseconds added, wrapping around midnight.
 */
fun LocalTime.plusNanoseconds(nanoseconds: Long): LocalTime =
    fromWrappedNanos(toNanosecondOfDay() + nanoseconds)

fun LocalTime.minusNanoseconds(nanoseconds: Long): LocalTime = plusNanoseconds(-nanoseconds)

/**
 * Returns a copy of this LocalTime with the hour field set to the given value.
 */
fun LocalTime.withHour(hour: Int): LocalTime = LocalTime(hour, minute, second, nanosecond)

/**
 * Returns a copy of this LocalTime with the minute field set to the given value.
 */
fun LocalTime.withMinute(minute: Int): LocalTime = LocalTime(hour, minute, second, nanosecond)

/**
 * Returns a copy of this LocalTime with the second field set to the given value.
 */
fun LocalTime.withSecond(second: Int): LocalTime = LocalTime(hour, minute, second, nanosecond)

/**
 * Returns a copy of this LocalTime with the nanosecond field set to the given value.
 */
fun LocalTime.withNanosecond(nanosecond: Int): LocalTime = LocalTime(hour, minute, second, nanosecond)

private fun fromWrappedNanos(nanos: Long): LocalTime {
    val wrapped = ((nanos % NANOS_PER_DAY) + NANOS_PER_DAY) % NANOS_PER_DAY
    return LocalTime.fromNanosecondOfDay(wrapped)
}
