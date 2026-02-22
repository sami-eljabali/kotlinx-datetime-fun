package kotlinxdatetimefun.localdate.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

@OptIn(FormatStringsInDatetimeFormats::class)
fun LocalDate.print(format: String): String {
    val dateFormat = LocalDate.Format {
        byUnicodePattern(format)
    }
    return dateFormat.format(this)
}