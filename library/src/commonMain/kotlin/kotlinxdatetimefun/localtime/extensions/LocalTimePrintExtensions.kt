package kotlinxdatetimefun.localtime.extensions

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalTime.print(format: String): String {
    val dateFormat = LocalTime.Format {
        byUnicodePattern(format)
    }
    return dateFormat.format(this)
}