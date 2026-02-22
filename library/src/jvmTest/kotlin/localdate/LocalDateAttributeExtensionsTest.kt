package localdate

import kotlinx.datetime.LocalDate
import kotlinxdatetimefun.localdate.extensions.getDaysInMonth
import kotlinxdatetimefun.localdate.extensions.getMonthBaseZero
import kotlinxdatetimefun.localdate.extensions.isInLeapYear
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LocalDateAttributeExtensionsTest {

    @Test
    fun `isInLeapYear returns true for leap year and false for non-leap year`() {
        // Leap year example: 2020 is a leap year.
        val leapYearDate = LocalDate(2020, 2, 29)
        assertTrue(leapYearDate.isInLeapYear(), "Expected 2020 to be a leap year")

        // Non-leap year example: 2019 is not a leap year.
        val nonLeapYearDate = LocalDate(2019, 2, 28)
        assertFalse(nonLeapYearDate.isInLeapYear(), "Expected 2019 to not be a leap year")
    }

    @Test
    fun `getMonthBaseZero returns zero-based month index`() {
        // January should be 0 (since January.value = 1)
        val january = LocalDate(2021, 1, 15)
        assertEquals(0, january.getMonthBaseZero())

        // February should be 1 (since February.value = 2)
        val february = LocalDate(2021, 2, 15)
        assertEquals(1, february.getMonthBaseZero())

        // December should be 11 (since December.value = 12)
        val december = LocalDate(2021, 12, 15)
        assertEquals(11, december.getMonthBaseZero())
    }

    @Test
    fun `getDaysInMonth returns correct number of days in month`() {
        // February in a leap year (2020) should have 29 days.
        val febLeap = LocalDate(2020, 2, 15)
        assertEquals(29, febLeap.getDaysInMonth(), "Expected February 2020 to have 29 days")

        // February in a non-leap year (2019) should have 28 days.
        val febNonLeap = LocalDate(2019, 2, 15)
        assertEquals(28, febNonLeap.getDaysInMonth(), "Expected February 2019 to have 28 days")

        // April should always have 30 days.
        val april = LocalDate(2021, 4, 10)
        assertEquals(30, april.getDaysInMonth(), "Expected April to have 30 days")

        // December should always have 31 days.
        val december = LocalDate(2021, 12, 10)
        assertEquals(31, december.getDaysInMonth(), "Expected December to have 31 days")
    }
}
