package kotlinxdatetimefun.localdate

import kotlin.time.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDate.Companion.of(
    epochMilliseconds: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault()
): LocalDate =
    Instant.fromEpochMilliseconds(epochMilliseconds)
        .toLocalDateTime(timeZone)
        .date