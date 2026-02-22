package kotlinxdatetimefun.localdatetime.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDateTime.print(format: String): String {
    val dateFormat = LocalDateTime.Format {
        byUnicodePattern(format)
    }
    return dateFormat.format(this)
}