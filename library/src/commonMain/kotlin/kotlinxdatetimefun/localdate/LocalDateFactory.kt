package kotlinxdatetimefun.localdate

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun LocalDate.Companion.of(
    epochMilliseconds: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDate =
    Instant
        .fromEpochMilliseconds(epochMilliseconds)
        .toLocalDateTime(timeZone)
        .date
