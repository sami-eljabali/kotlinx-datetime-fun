package localdatetime

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinxdatetimefun.localdatetime.extensions.atEndOfDay
import kotlinxdatetimefun.localdatetime.extensions.atStartOfDay
import kotlinxdatetimefun.localdatetime.extensions.getLast
import kotlinxdatetimefun.localdatetime.extensions.getNext
import kotlinxdatetimefun.localdatetime.extensions.minusDays
import kotlinxdatetimefun.localdatetime.extensions.minusHours
import kotlinxdatetimefun.localdatetime.extensions.minusMinutes
import kotlinxdatetimefun.localdatetime.extensions.minusMonths
import kotlinxdatetimefun.localdatetime.extensions.minusSeconds
import kotlinxdatetimefun.localdatetime.extensions.minusYears
import kotlinxdatetimefun.localdatetime.extensions.plusDays
import kotlinxdatetimefun.localdatetime.extensions.plusHours
import kotlinxdatetimefun.localdatetime.extensions.plusMinutes
import kotlinxdatetimefun.localdatetime.extensions.plusMonths
import kotlinxdatetimefun.localdatetime.extensions.plusSeconds
import kotlinxdatetimefun.localdatetime.extensions.plusYears
import kotlinxdatetimefun.localdatetime.extensions.toLocalDate
import kotlinxdatetimefun.localdatetime.extensions.toLocalTime
import kotlinxdatetimefun.localdatetime.extensions.withLocalTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LocalDateTimeMutatingExtensionsTest {

    // Test data
    private val testDateTime = LocalDateTime(
        year = 2023,
        monthNumber = 6,
        dayOfMonth = 15,
        hour = 12,
        minute = 30,
        second = 45,
        nanosecond = 123_456_789
    )
    private val testTime = LocalTime(
        hour = 8,
        minute = 15,
        second = 30,
        nanosecond = 500_000_000
    )
    private val testTimeZone = TimeZone.of("UTC")

    @Test
    fun `toLocalDate should extract correct date components`() {
        val result = testDateTime.toLocalDate()
        assertEquals(2023, result.year)
        assertEquals(6, result.monthNumber)
        assertEquals(15, result.dayOfMonth)
    }

    @Test
    fun `toLocalTime should extract correct time components`() {
        val result = testDateTime.toLocalTime()
        assertEquals(12, result.hour)
        assertEquals(30, result.minute)
        assertEquals(45, result.second)
        assertEquals(123_456_789, result.nanosecond)
    }

    @Test
    fun `atStartOfDay should set time to midnight`() {
        val result = testDateTime.atStartOfDay()
        assertEquals(0, result.hour)
        assertEquals(0, result.minute)
        assertEquals(0, result.second)
        assertEquals(0, result.nanosecond)
        assertEquals(testDateTime.year, result.year)
        assertEquals(testDateTime.monthNumber, result.monthNumber)
        assertEquals(testDateTime.dayOfMonth, result.dayOfMonth)
    }

    @Test
    fun `atEndOfDay should set time to last moment of day`() {
        val result = testDateTime.atEndOfDay()
        assertEquals(23, result.hour)
        assertEquals(59, result.minute)
        assertEquals(59, result.second)
        assertEquals(999_999_999, result.nanosecond)
        assertEquals(testDateTime.year, result.year)
        assertEquals(testDateTime.monthNumber, result.monthNumber)
        assertEquals(testDateTime.dayOfMonth, result.dayOfMonth)
    }

    @Test
    fun `withLocalTime should combine date with new time`() {
        val result = testDateTime.withLocalTime(testTime)
        assertEquals(testDateTime.year, result.year)
        assertEquals(testDateTime.monthNumber, result.monthNumber)
        assertEquals(testDateTime.dayOfMonth, result.dayOfMonth)
        assertEquals(testTime.hour, result.hour)
        assertEquals(testTime.minute, result.minute)
        assertEquals(testTime.second, result.second)
        assertEquals(testTime.nanosecond, result.nanosecond)
    }

    @Test
    fun `plusYears should correctly add years`() {
        val result = testDateTime.plusYears(2, testTimeZone)
        assertEquals(2025, result.year)
        assertEquals(testDateTime.monthNumber, result.monthNumber)
        assertEquals(testDateTime.dayOfMonth, result.dayOfMonth)
    }

    @Test
    fun `minusYears should correctly subtract years`() {
        val result = testDateTime.minusYears(3, testTimeZone)
        assertEquals(2020, result.year)
        assertEquals(testDateTime.monthNumber, result.monthNumber)
        assertEquals(testDateTime.dayOfMonth, result.dayOfMonth)
    }

    @Test
    fun `plusMonths should correctly add months`() {
        val result = testDateTime.plusMonths(4, testTimeZone)
        assertEquals(2023, result.year)
        assertEquals(10, result.monthNumber)
        assertEquals(15, result.dayOfMonth)
    }

    @Test
    fun `minusMonths should correctly subtract months`() {
        val result = testDateTime.minusMonths(5, testTimeZone)
        assertEquals(2023, result.year)
        assertEquals(1, result.monthNumber)
        assertEquals(15, result.dayOfMonth)
    }

    @Test
    fun `plusDays should correctly add days`() {
        val result = testDateTime.plusDays(10, testTimeZone)
        assertEquals(2023, result.year)
        assertEquals(6, result.monthNumber)
        assertEquals(25, result.dayOfMonth)
    }

    @Test
    fun `minusDays should correctly subtract days`() {
        val result = testDateTime.minusDays(15, testTimeZone)
        assertEquals(2023, result.year)
        assertEquals(5, result.monthNumber)
        assertEquals(31, result.dayOfMonth)
    }

    @Test
    fun `plusHours should correctly add hours`() {
        val result = testDateTime.plusHours(6, testTimeZone)
        assertEquals(2023, result.year)
        assertEquals(6, result.monthNumber)
        assertEquals(15, result.dayOfMonth)
        assertEquals(18, result.hour)
    }

    @Test
    fun `minusHours should correctly subtract hours`() {
        val result = testDateTime.minusHours(13, testTimeZone)
        assertEquals(2023, result.year)
        assertEquals(6, result.monthNumber)
        assertEquals(14, result.dayOfMonth)
        assertEquals(23, result.hour)
    }

    @Test
    fun `plusMinutes should correctly add minutes`() {
        val result = testDateTime.plusMinutes(45, testTimeZone)
        assertEquals(13, result.hour)
        assertEquals(15, result.minute)
    }

    @Test
    fun `minusMinutes should correctly subtract minutes`() {
        val result = testDateTime.minusMinutes(35, testTimeZone)
        assertEquals(11, result.hour)
        assertEquals(55, result.minute)
    }

    @Test
    fun `plusSeconds should correctly add seconds`() {
        val result = testDateTime.plusSeconds(20, testTimeZone)
        assertEquals(12, result.hour)
        assertEquals(31, result.minute)
        assertEquals(5, result.second)
    }

    @Test
    fun `minusSeconds should correctly subtract seconds`() {
        val result = testDateTime.minusSeconds(50, testTimeZone)
        assertEquals(12, result.hour)
        assertEquals(29, result.minute)
        assertEquals(55, result.second)
    }

    @Test
    fun `getLast should find previous day of week`() {
        // June 15, 2023 is a Thursday
        val previousMonday = testDateTime.getLast(DayOfWeek.MONDAY, false, testTimeZone)
        assertEquals(2023, previousMonday.year)
        assertEquals(6, previousMonday.monthNumber)
        assertEquals(12, previousMonday.dayOfMonth)
        assertEquals(DayOfWeek.MONDAY, previousMonday.dayOfWeek)
    }

    @Test
    fun `getLast should return same day when countingInThisDay is true`() {
        val sameDay = testDateTime.getLast(DayOfWeek.THURSDAY, true, testTimeZone)
        assertEquals(testDateTime.year, sameDay.year)
        assertEquals(testDateTime.monthNumber, sameDay.monthNumber)
        assertEquals(testDateTime.dayOfMonth, sameDay.dayOfMonth)
    }

    @Test
    fun `getNext should find next day of week`() {
        // June 15, 2023 is a Thursday
        val nextFriday = testDateTime.getNext(DayOfWeek.FRIDAY, false, testTimeZone)
        assertEquals(2023, nextFriday.year)
        assertEquals(6, nextFriday.monthNumber)
        assertEquals(16, nextFriday.dayOfMonth)
        assertEquals(DayOfWeek.FRIDAY, nextFriday.dayOfWeek)
    }

    @Test
    fun `getNext should return same day when countingInThisDay is true`() {
        val sameDay = testDateTime.getNext(DayOfWeek.THURSDAY, true, testTimeZone)
        assertEquals(testDateTime.year, sameDay.year)
        assertEquals(testDateTime.monthNumber, sameDay.monthNumber)
        assertEquals(testDateTime.dayOfMonth, sameDay.dayOfMonth)
    }

    @Test
    fun `should handle leap year correctly in date calculations`() {
        val leapDate = LocalDateTime(2020, 2, 29, 12, 0)
        val result = leapDate.plusYears(1, testTimeZone)
        assertEquals(2021, result.year)
        assertEquals(2, result.monthNumber)
        assertEquals(28, result.dayOfMonth)
    }

    @Test
    fun `should handle month boundary in date calculations`() {
        val monthEnd = LocalDateTime(2023, 1, 31, 12, 0)
        val result = monthEnd.plusMonths(1, testTimeZone)
        assertEquals(2023, result.year)
        assertEquals(2, result.monthNumber)
        assertEquals(28, result.dayOfMonth)
    }
}