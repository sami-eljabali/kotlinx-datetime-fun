package localdate

import kotlinx.datetime.LocalDate
import kotlinxdatetimefun.localdate.extensions.toLocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class LocalDateParsingExtensionsTest {

    @Test
    fun `toLocalDate returns LocalDate for valid ISO date string when no format is provided`() {
        // Given a valid ISO date string
        val dateStr = "2021-09-15"
        // When converting without a custom format
        val result = dateStr.toLocalDate()
        // Then the result should be the expected LocalDate
        assertEquals(LocalDate(2021, 9, 15), result)
    }

    @Test
    fun `toLocalDate returns null for invalid date string when no format is provided`() {
        // Given an invalid ISO date string (wrong format)
        val dateStr = "15-09-2021"
        // When converting without a custom format
        val result = dateStr.toLocalDate()
        // Then the result should be null since parsing fails
        assertNull(result)
    }

    @Test
    fun `toLocalDate returns LocalDate for valid date string using provided custom format`() {
        // Given a valid date string in custom format
        val dateStr = "15/09/2021"
        val format = "dd/MM/yyyy"
        // When converting using the provided format
        val result = dateStr.toLocalDate(format)
        // Then the result should be the expected LocalDate
        assertEquals(LocalDate(2021, 9, 15), result)
    }

    @Test
    fun `toLocalDate returns null when provided custom format does not match date string`() {
        // Given a valid date string that does not match the provided format
        val dateStr = "15/09/2021"
        val format = "MM-dd-yyyy" // Mismatched pattern
        // When converting using the mismatched format
        val result = dateStr.toLocalDate(format)
        // Then the result should be null since parsing fails
        assertNull(result)
    }

    @Test
    fun `toLocalDate with empty format should fallback to default ISO parsing`() {
        // Given a valid ISO date string and an empty format
        val dateStr = "2021-09-15"
        // When converting with an empty format string
        val result = dateStr.toLocalDate("")
        // Then the result should be the expected LocalDate using ISO parsing
        assertEquals(LocalDate(2021, 9, 15), result)
    }
}