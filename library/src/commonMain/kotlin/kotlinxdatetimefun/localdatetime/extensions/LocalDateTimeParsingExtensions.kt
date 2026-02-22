package kotlinxdatetimefun.localdatetime.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

private const val flexibleIso8601Format = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]['Z']"

/**
 * Works off of String representations of datetime and tries parsing as LocalDateTime & return null if it fails.
 *
 * @param this String representation of LocalDateTime.
 * @param format String representing format that should solely be used when parsing the date.
 * @return LocalDateTime? Null means couldn't parse, else parsed LocalDateTime.
 */
fun String.toLocalDateTime(format: String? = null): LocalDateTime? =
    parseLocalDateTimeHelper(this, format) ?: parseLocalDateTimeHelper(this, flexibleIso8601Format)

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