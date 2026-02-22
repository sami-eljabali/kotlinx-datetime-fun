package kotlinxdatetimefun.localtime.extensions

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

fun String.toLocalTime(format: String? = null): LocalTime? =
    if (format.isNullOrEmpty()) {
        try {
            LocalTime.parse(this)
        } catch (e: IllegalArgumentException) {
            null
        }
    } else {
        try {
            @OptIn(FormatStringsInDatetimeFormats::class)
            val localTimeFormat = LocalTime.Format {
                byUnicodePattern(format)
            }
            localTimeFormat.parse(format)
        } catch (e: IllegalArgumentException) {
            null
        }
    }