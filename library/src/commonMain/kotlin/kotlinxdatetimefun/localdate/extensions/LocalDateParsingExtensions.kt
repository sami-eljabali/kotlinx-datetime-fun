package kotlinxdatetimefun.localdate.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

/**
 * Works off of String representations of date, without time, nor time zone.
 * When a format is present, it'll try parsing using that format alone, & return null if it fails.
 *
 * @param this String representation of LocalDate.
 * @param format String representing format that should solely be used when parsing the date.
 * @return LocalDate? Null means couldn't parse, else parsed LocalDate.
 */
@OptIn(FormatStringsInDatetimeFormats::class)
fun String.toLocalDate(format: String? = null): LocalDate? =
    if (format.isNullOrEmpty()) {
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