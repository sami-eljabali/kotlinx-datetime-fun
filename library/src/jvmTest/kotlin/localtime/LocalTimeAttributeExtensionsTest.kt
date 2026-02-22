package localtime

import kotlinx.datetime.LocalTime
import kotlinxdatetimefun.localtime.MAX
import kotlinxdatetimefun.localtime.MIN
import kotlinxdatetimefun.localtime.extensions.isAtEndOfDay
import kotlinxdatetimefun.localtime.extensions.isAtStartOfDay
import kotlinxdatetimefun.localtime.extensions.isInAm
import kotlinxdatetimefun.localtime.extensions.isInPm
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LocalTimeAttributeExtensionsTest {

    @Test
    fun `test isAtStartOfDay returns true for MIN`() {
        assertTrue(LocalTime.MIN.isAtStartOfDay())
    }

    @Test
    fun `test isAtStartOfDay returns false for other times`() {
        assertFalse(LocalTime(0, 1).isAtStartOfDay())
    }

    @Test
    fun `test isAtEndOfDay returns true for MAX`() {
        assertTrue(LocalTime.MAX.isAtEndOfDay())
    }

    @Test
    fun `test isAtEndOfDay returns false for other times`() {
        assertFalse(LocalTime(23, 59, 59, 999_999_998).isAtEndOfDay())
    }

    @Test
    fun `test isInAm returns true for early morning`() {
        assertTrue(LocalTime(6, 30).isInAm())
    }

    @Test
    fun `test isInAm returns false for noon or later`() {
        assertFalse(LocalTime(12, 0).isInAm())
        assertFalse(LocalTime(15, 0).isInAm())
    }

    @Test
    fun `test isInPm returns true for noon or later`() {
        assertTrue(LocalTime(12, 0).isInPm())
        assertTrue(LocalTime(18, 30).isInPm())
    }

    @Test
    fun `test isInPm returns false for before noon`() {
        assertFalse(LocalTime(11, 59, 59).isInPm())
    }
}