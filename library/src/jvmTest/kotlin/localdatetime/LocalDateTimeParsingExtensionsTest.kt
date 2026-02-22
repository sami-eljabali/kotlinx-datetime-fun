package localdatetime

import kotlinx.datetime.LocalDateTime
import kotlinxdatetimefun.localdatetime.extensions.print
import kotlinxdatetimefun.localdatetime.extensions.toLocalDateTime
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LocalDateTimeParsingExtensionsTest {

    companion object {
        private const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val ISO_8601_S = "yyyy-MM-dd'T'HH:mm:ss.S'Z'"
        private const val ISO_8601_SS = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'"
        private const val ISO_8601_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val ISO_8601_4S = "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"
        private const val ISO_8601_5S = "yyyy-MM-dd'T'HH:mm:ss.SSSSS'Z'"
        private const val ISO_8601_6S = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
    }

    @Test
    fun `test valid ISO date time without format`() {
        val input = "2024-04-13T12:34:56"
        val result = input.toLocalDateTime()
        assertNotNull(result)
        assertEquals(LocalDateTime(2024, 4, 13, 12, 34, 56), result)
    }

    @Test
    fun `test invalid date without format returns null`() {
        val input = "not-a-date"
        val result = input.toLocalDateTime()
        assertNull(result)
    }

    @Test
    fun `test valid formatted date time with format`() {
        val input = "13-04-2024 12:34:56"
        val format = "dd-MM-yyyy HH:mm:ss"
        val result = input.toLocalDateTime(format)
        assertNotNull(result)
        assertEquals(LocalDateTime(2024, 4, 13, 12, 34, 56), result)
    }

    @Test
    fun `test valid ISO date time with incorrect format returns null`() {
        val input = "2024-04-13T12:34:56"
        val format = "dd-MM-yyyy HH:mm:ss" // doesn't match the input
        val result = input.toLocalDateTime(format)
        assertNotNull(result)
    }

    @Test
    fun `test null format behaves as no format`() {
        val input = "2024-04-13T08:00:00"
        val result1 = input.toLocalDateTime()
        val result2 = input.toLocalDateTime(null)
        assertEquals(result1, result2)
    }

    @Test
    fun `test empty string returns null`() {
        val input = ""
        val result = input.toLocalDateTime()
        assertNull(result)
    }


    @Test
    fun `given date in ISO_8601, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56"
        val dateInTextWithZ = "${dateInText}Z"

        // when
        val dateParsed: LocalDateTime = dateInTextWithZ.toLocalDateTime() ?: throw RuntimeException("Failed to parse")
        val dateParsedWithZ: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInTextWithZ, dateParsed.print(ISO_8601))
        assertEquals(dateInTextWithZ, dateParsedWithZ.print(ISO_8601))
        assertEquals("2023-07-26T12:34:56.000000Z", dateParsed.print(ISO_8601_6S))
        assertEquals("2023-07-26T12:34:56.000000Z", dateParsedWithZ.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_S, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.1Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_S))
        assertEquals("2023-07-26T12:34:56.100000Z", dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_SS, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.12Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_SS))
        assertEquals("2023-07-26T12:34:56.120000Z", dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_SSS, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.123Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_SSS))
        assertEquals("2023-07-26T12:34:56.123000Z", dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_4S, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.1234Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_4S))
        assertEquals("2023-07-26T12:34:56.123400Z", dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_5S, when parsed without format & converted to date, then should be null`() {
        // given
        val dateInText = "2023-07-26T12:34:56.12345"
        val dateInTextWithZ = "${dateInText}Z"

        // when
        val dateParsed: LocalDateTime = dateInTextWithZ.toLocalDateTime() ?: throw RuntimeException("Failed to parse")
        val dateParsedWithZ: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInTextWithZ, dateParsed.print(ISO_8601_5S))
        assertEquals(dateInTextWithZ, dateParsedWithZ.print(ISO_8601_5S))
        assertEquals("2023-07-26T12:34:56.123450Z", dateParsed.print(ISO_8601_6S))
        assertEquals("2023-07-26T12:34:56.123450Z", dateParsedWithZ.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_6S, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.123456Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_6S))
    }
}
