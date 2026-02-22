package localtime

import kotlinx.datetime.LocalTime
import kotlinxdatetimefun.localtime.extensions.print
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LocalTimeParsingExtensionsTest {

    private val testTime = LocalTime(14, 5, 9, 123_000_000) // 14:05:09.123

    @Test
    fun `test formatting with HH colon mm`() {
        val result = testTime.print("HH:mm")
        assertEquals("14:05", result)
    }

    @Test
    fun `test formatting with full time pattern including seconds`() {
        val result = testTime.print("HH:mm:ss")
        assertEquals("14:05:09", result)
    }

    @Disabled
    @Test
    fun `test formatting with 12-hour format and AM_PM`() {
        val result = testTime.print("hh:mm a")
        assertEquals("02:05 PM", result)
    }

    @Test
    fun `test formatting with fractional seconds`() {
        val result = testTime.print("HH:mm:ss.SSS")
        assertEquals("14:05:09.123", result)
    }

    @Test
    fun `test formatting with literal text`() {
        val result = testTime.print("'Time is' HH:mm")
        assertEquals("Time is 14:05", result)
    }

    @Test
    fun `test invalid format throws IllegalArgumentException`() {
        val invalidFormat = "invalid%Q"
        assertThrows<IllegalArgumentException> {
            testTime.print(invalidFormat)
        }
    }

    @Test
    fun `test formatting midnight time`() {
        val midnight = LocalTime(0, 0, 0)
        val result = midnight.print("HH:mm:ss")
        assertEquals("00:00:00", result)
    }

    @Test
    fun `test formatting with single digit hour and minute`() {
        val earlyTime = LocalTime(5, 7)
        val result = earlyTime.print("H:m")
        assertEquals("5:7", result)
    }
}

