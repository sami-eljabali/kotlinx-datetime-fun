package localdate

import kotlinx.datetime.LocalDate
import kotlinxdatetimefun.localdate.extensions.compareDay
import kotlinxdatetimefun.localdate.extensions.compareMonth
import kotlinxdatetimefun.localdate.extensions.compareYear
import kotlinxdatetimefun.localdate.extensions.getDayDifference
import kotlinxdatetimefun.localdate.extensions.getHourDifference
import kotlinxdatetimefun.localdate.extensions.getMilliSecondDifference
import kotlinxdatetimefun.localdate.extensions.getMinuteDifference
import kotlinxdatetimefun.localdate.extensions.getMonthDifference
import kotlinxdatetimefun.localdate.extensions.getSecondDifference
import kotlinxdatetimefun.localdate.extensions.getYearDifference
import kotlinxdatetimefun.localdate.extensions.isAfterDay
import kotlinxdatetimefun.localdate.extensions.isAfterEqualDay
import kotlinxdatetimefun.localdate.extensions.isAfterEqualMonth
import kotlinxdatetimefun.localdate.extensions.isAfterEqualYear
import kotlinxdatetimefun.localdate.extensions.isAfterMonth
import kotlinxdatetimefun.localdate.extensions.isAfterYear
import kotlinxdatetimefun.localdate.extensions.isBeforeDay
import kotlinxdatetimefun.localdate.extensions.isBeforeEqualDay
import kotlinxdatetimefun.localdate.extensions.isBeforeEqualMonth
import kotlinxdatetimefun.localdate.extensions.isBeforeEqualYear
import kotlinxdatetimefun.localdate.extensions.isBeforeMonth
import kotlinxdatetimefun.localdate.extensions.isBeforeYear
import kotlinxdatetimefun.localdate.extensions.isEqualDay
import kotlinxdatetimefun.localdate.extensions.isEqualMonth
import kotlinxdatetimefun.localdate.extensions.isEqualYear
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LocalDateComparisonExtensionsTest {

    @Test
    fun `given 2 dates 0 days apart, then should see such differences in comparing`() {
        // given
        // 2021-06-08
        val dateA = LocalDate(
            year = 2021,
            monthNumber = 6,
            dayOfMonth = 8,
        )

        // 2021-06-08
        val dateB = LocalDate(
            year = 2021,
            monthNumber = 6,
            dayOfMonth = 8,
        )

        // when
        val actualYearsDifference = dateA.getYearDifference(dateB)
        val actualMonthDifference = dateA.getMonthDifference(dateB)
        val actualDayDifference = dateA.getDayDifference(dateB)
        val actualHourDifference = dateA.getHourDifference(dateB)
        val actualMinuteDifference = dateA.getMinuteDifference(dateB)
        val actualSecondDifference = dateA.getSecondDifference(dateB)
        val actualMilliSecondDifference = dateA.getMilliSecondDifference(dateB)

        // then
        assertEquals(0, actualYearsDifference)
        assertEquals(0, actualMonthDifference)
        assertEquals(0, actualDayDifference)
        assertEquals(0, actualHourDifference)
        assertEquals(0, actualMinuteDifference)
        assertEquals(0, actualSecondDifference)
        assertEquals(0, actualMilliSecondDifference)

        // day comparisons
        assertEquals(0, dateA.compareDay(dateB))
        assertTrue(dateA.isEqualDay(dateB))
        assertFalse(dateA.isBeforeDay(dateB))
        assertTrue(dateA.isBeforeEqualDay(dateB))
        assertTrue(dateA.isBeforeEqualDay(dateB))
        assertFalse(dateA.isAfterDay(dateB))
        assertTrue(dateA.isAfterEqualDay(dateB))

        // Month Comparisons
        assertEquals(0, dateA.compareMonth(dateB))
        assertTrue(dateA.isEqualMonth(dateB))
        assertFalse(dateA.isBeforeMonth(dateB))
        assertTrue(dateA.isBeforeEqualMonth(dateB))
        assertTrue(dateA.isBeforeEqualMonth(dateB))
        assertFalse(dateA.isAfterMonth(dateB))
        assertTrue(dateA.isAfterEqualMonth(dateB))

        // Year Comparisons
        assertEquals(0, dateA.compareYear(dateB))
        assertTrue(dateA.isEqualYear(dateB))
        assertFalse(dateA.isBeforeYear(dateB))
        assertTrue(dateA.isBeforeEqualYear(dateB))
        assertTrue(dateA.isBeforeEqualYear(dateB))
        assertFalse(dateA.isAfterYear(dateB))
        assertTrue(dateA.isAfterEqualYear(dateB))
    }

    @Test
    fun `given 2 dateTimes 3 years apart, then should see such differences in comparing`() {
        // given
        // 2021-06-08
        val dateA = LocalDate(
            year = 2021,
            monthNumber = 6,
            dayOfMonth = 8,
        )

        // 2024-06-08
        val dateB = LocalDate(
            year = 2024,
            monthNumber = 6,
            dayOfMonth = 8,
        )

        // when
        val actualYearsDifference = dateA.getYearDifference(dateB)
        val actualMonthDifference = dateA.getMonthDifference(dateB)
        val actualDayDifference = dateA.getDayDifference(dateB)
        val actualHourDifference = dateA.getHourDifference(dateB)
        val actualMinuteDifference = dateA.getMinuteDifference(dateB)
        val actualSecondDifference = dateA.getSecondDifference(dateB)
        val actualMilliSecondDifference = dateA.getMilliSecondDifference(dateB)

        // then
        assertEquals(3, actualYearsDifference)
        assertEquals(36, actualMonthDifference)
        assertEquals(1096, actualDayDifference)
        assertEquals(26304, actualHourDifference)
        assertEquals(1_578_240, actualMinuteDifference)
        assertEquals(94_694_400, actualSecondDifference)
        assertEquals(94_694_400_000, actualMilliSecondDifference)

        // day comparisons
        assertEquals(-1, dateA.compareDay(dateB))
        assertFalse(dateA.isEqualDay(dateB))
        assertTrue(dateA.isBeforeDay(dateB))
        assertTrue(dateA.isBeforeEqualDay(dateB))
        assertFalse(dateA.isAfterDay(dateB))
        assertFalse(dateA.isAfterEqualDay(dateB))

        // Month Comparisons
        assertEquals(-1, dateA.compareMonth(dateB))
        assertFalse(dateA.isEqualMonth(dateB))
        assertTrue(dateA.isBeforeMonth(dateB))
        assertTrue(dateA.isBeforeEqualMonth(dateB))
        assertFalse(dateA.isAfterMonth(dateB))
        assertFalse(dateA.isAfterEqualMonth(dateB))

        // Year Comparisons
        assertEquals(-1, dateA.compareYear(dateB))
        assertFalse(dateA.isEqualYear(dateB))
        assertTrue(dateA.isBeforeYear(dateB))
        assertTrue(dateA.isBeforeEqualYear(dateB))
        assertFalse(dateA.isAfterYear(dateB))
        assertFalse(dateA.isAfterEqualYear(dateB))
    }

    @Test
    fun `given dateA after dateB with differing months and days, then should see correct differences and comparisons`() {
        // given
        // 2024-08-10
        val dateA = LocalDate(
            year = 2024,
            monthNumber = 8,
            dayOfMonth = 10,
        )

        // 2021-03-05
        val dateB = LocalDate(
            year = 2021,
            monthNumber = 3,
            dayOfMonth = 5,
        )

        // when
        val actualYearsDifference = dateA.getYearDifference(dateB)
        val actualMonthDifference = dateA.getMonthDifference(dateB)
        val actualDayDifference = dateA.getDayDifference(dateB)
        val actualHourDifference = dateA.getHourDifference(dateB)
        val actualMinuteDifference = dateA.getMinuteDifference(dateB)
        val actualSecondDifference = dateA.getSecondDifference(dateB)
        val actualMilliSecondDifference = dateA.getMilliSecondDifference(dateB)

        // then
        assertEquals(-3, actualYearsDifference)
        assertEquals(-41, actualMonthDifference)
        assertEquals(-1254, actualDayDifference)
        assertEquals(-30_096, actualHourDifference)
        assertEquals(-1_805_760, actualMinuteDifference)
        assertEquals(-108_345_600, actualSecondDifference)
        assertEquals(-108_345_600_000, actualMilliSecondDifference)

        // Day comparisons
        assertEquals(1, dateA.compareDay(dateB))
        assertFalse(dateA.isEqualDay(dateB))
        assertFalse(dateA.isBeforeDay(dateB))
        assertFalse(dateA.isBeforeEqualDay(dateB))
        assertTrue(dateA.isAfterDay(dateB))
        assertTrue(dateA.isAfterEqualDay(dateB))

        // Month Comparisons
        assertEquals(1, dateA.compareMonth(dateB))
        assertFalse(dateA.isEqualMonth(dateB))
        assertFalse(dateA.isBeforeMonth(dateB))
        assertFalse(dateA.isBeforeEqualMonth(dateB))
        assertTrue(dateA.isAfterMonth(dateB))
        assertTrue(dateA.isAfterEqualMonth(dateB))

        // Year Comparisons
        assertEquals(1, dateA.compareYear(dateB))
        assertFalse(dateA.isEqualYear(dateB))
        assertFalse(dateA.isBeforeYear(dateB))
        assertFalse(dateA.isBeforeEqualYear(dateB))
        assertTrue(dateA.isAfterYear(dateB))
        assertTrue(dateA.isAfterEqualYear(dateB))
    }
}