package kotlinxdatetimefun.localtime.extensions

import kotlinx.datetime.LocalTime
import kotlinxdatetimefun.localtime.MAX
import kotlinxdatetimefun.localtime.MIN
import kotlinxdatetimefun.localtime.NOON

fun LocalTime.isAtStartOfDay(): Boolean = this == LocalTime.MIN

fun LocalTime.isAtEndOfDay(): Boolean = this == LocalTime.MAX

fun LocalTime.isInAm(): Boolean = this < LocalTime.NOON

fun LocalTime.isInPm(): Boolean = this >= LocalTime.NOON