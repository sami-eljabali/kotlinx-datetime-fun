package kotlinxdatetimefun.localdatetime.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

private const val flexibleIso8601Format = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]['Z']"

/**
 * Works off of String representations of datetime and tries parsing as LocalDateTime & return null if it fails.
 *
 * @param this String representation of LocalDateTime.
 * @param formats Parsing formats attempted in order. Null/blank entries attempt default ISO parsing.
 * @return LocalDateTime? Null means couldn't parse, else parsed LocalDateTime.
 */
fun String.toLocalDateTime(vararg formats: String?): LocalDateTime? {
    val parsingAttempts = if (formats.isEmpty()) listOf<String?>(null) else formats.asList()
    for (format in parsingAttempts) {
        val parsedDateTime = parseLocalDateTimeHelper(this, format)
        if (parsedDateTime != null) return parsedDateTime
    }
    return parseLocalDateTimeHelper(this, flexibleIso8601Format)
}

private fun parseLocalDateTimeHelper(dateText: String, format: String?): LocalDateTime? =
    if (format.isNullOrEmpty())
        try {
            LocalDateTime.parse(dateText)
        } catch (e: IllegalArgumentException) {
            null
        }
    else {
        try {
            @OptIn(FormatStringsInDatetimeFormats::class)
            val dateTimeFormat = LocalDateTime.Format {
                byUnicodePattern(format)
            }
            dateTimeFormat.parse(dateText)
        } catch (e: IllegalArgumentException) {
            null
        }
    }
