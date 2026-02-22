package kotlinxdatetimefun.localtime.extensions

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

fun String.toLocalTime(vararg formats: String?): LocalTime? {
    val parsingAttempts = if (formats.isEmpty()) listOf<String?>(null) else formats.asList()
    for (format in parsingAttempts) {
        val parsedTime = if (format.isNullOrBlank()) {
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
                localTimeFormat.parse(this)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
        if (parsedTime != null) return parsedTime
    }
    return null
}
