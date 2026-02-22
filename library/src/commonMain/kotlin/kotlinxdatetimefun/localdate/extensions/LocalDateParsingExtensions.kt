package kotlinxdatetimefun.localdate.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

/**
 * Works off of String representations of date, without time, nor time zone.
 * Every provided format is attempted in order until one succeeds.
 *
 * @param this String representation of LocalDate.
 * @param formats Optional parsing formats. Null/blank entries attempt default ISO parsing.
 * @return LocalDate? Null means couldn't parse, else parsed LocalDate.
 */
@OptIn(FormatStringsInDatetimeFormats::class)
fun String.toLocalDate(vararg formats: String?): LocalDate? {
    val parsingAttempts = if (formats.isEmpty()) listOf<String?>(null) else formats.asList()
    for (format in parsingAttempts) {
        val parsedDate = if (format.isNullOrBlank()) {
            try {
                LocalDate.parse(this)
            } catch (e: IllegalArgumentException) {
                null
            }
        } else {
            try {
                val localDateFormat = LocalDate.Format {
                    byUnicodePattern(format)
                }
                LocalDate.parse(this, localDateFormat)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
        if (parsedDate != null) return parsedDate
    }
    return null
}
