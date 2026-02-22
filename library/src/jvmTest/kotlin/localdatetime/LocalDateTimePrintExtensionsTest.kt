package localdatetime

import kotlinx.datetime.LocalDateTime
import kotlinxdatetimefun.localdatetime.extensions.print
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LocalDateTimePrintExtensionsTest {

    private val testDateTime = LocalDateTime(2024, 4, 13, 15, 45, 30)

    @Test
    fun `test formatting with valid pattern dd-MM-yyyy`() {
        val format = "dd-MM-yyyy"
        val result = testDateTime.print(format)
        assertEquals("13-04-2024", result)
    }

    @Test
    fun `test formatting with full date and time pattern`() {
        val format = "yyyy/MM/dd HH:mm:ss"
        val result = testDateTime.print(format)
        assertEquals("2024/04/13 15:45:30", result)
    }

    @Disabled
    @Test
    fun `test formatting with 12-hour time and AM PM`() {
        val format = "MM/dd/yyyy hh:mm a"
        val result = testDateTime.print(format)
        assertEquals("04/13/2024 03:45 PM", result)
    }

    @Test
    fun `test formatting with single digit month and day`() {
        val format = "M-d-yyyy"
        val result = testDateTime.print(format)
        assertEquals("4-13-2024", result)
    }

    @Test
    fun `test invalid format throws exception`() {
        val invalidFormat = "invalid-format-%Q"
        assertThrows<IllegalArgumentException> {
            testDateTime.print(invalidFormat)
        }
    }
}
