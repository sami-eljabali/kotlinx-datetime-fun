package localtime

import kotlinx.datetime.LocalTime
import kotlinxdatetimefun.localtime.extensions.getHourDifference
import kotlinxdatetimefun.localtime.extensions.getMilliSecondDifference
import kotlinxdatetimefun.localtime.extensions.getMinuteDifference
import kotlinxdatetimefun.localtime.extensions.getNanoSecondDifference
import kotlinxdatetimefun.localtime.extensions.getSecondDifference
import kotlinxdatetimefun.localtime.extensions.periodUntil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LocalTimeComparisonExtensionsTest {

    private val timeA = LocalTime(10, 15, 30, 500_000_000) // 10:15:30.500
    private val timeB = LocalTime(12, 45, 35, 750_000_000) // 12:45:35.750

    @Test
    fun `given 2 times 1hr and 10m apart, then should see such differences in comparing`() {
        // given
        // 3:30 PM
        val dateA = LocalTime(
            hour = 15,
            minute = 30,
            second = 0,
        )

        // 4:40 PM
        val dateB = LocalTime(
            hour = 16,
            minute = 40,
            second = 0,
        )

        // when
        val actualHourDifference = dateA.getHourDifference(dateB)
        val actualMinuteDifference = dateA.getMinuteDifference(dateB)
        val actualSecondDifference = dateA.getSecondDifference(dateB)
        val actualMilliDifference = dateA.getMilliSecondDifference(dateB)

        // then
        assertEquals(1, actualHourDifference)
        assertEquals(70, actualMinuteDifference)
        assertEquals(4200, actualSecondDifference)
        assertEquals(4200000, actualMilliDifference)
    }

    @Test
    fun `given 2 times and no time apart, then should see such differences in comparing`() {
        // given
        // 3:30 PM
        val dateA = LocalTime(
            hour = 15,
            minute = 30,
            second = 0,
        )

        // 3:30 PM
        val dateB = LocalTime(
            hour = 15,
            minute = 30,
            second = 0,
        )

        // when
        val actualHourDifference = dateA.getHourDifference(dateB)
        val actualMinuteDifference = dateA.getMinuteDifference(dateB)
        val actualSecondDifference = dateA.getSecondDifference(dateB)
        val actualMilliSecondDifference = dateA.getMilliSecondDifference(dateB)

        // then
        assertEquals(0, actualHourDifference)
        assertEquals(0, actualMinuteDifference)
        assertEquals(0, actualSecondDifference)
        assertEquals(0, actualMilliSecondDifference)
    }

    @Test
    fun `test periodUntil returns correct hours, minutes, seconds, nanoseconds`() {
        val period = timeA.periodUntil(timeB)
        assertEquals(2, period.hours)
        assertEquals(30, period.minutes)
        assertEquals(5, period.seconds)
        assertEquals(250_000_000, period.nanoseconds)
    }

    @Test
    fun `test getNanoSecondDifference returns correct total nanoseconds`() {
        val diff = timeA.getNanoSecondDifference(timeB)
        // 2h 30m 5s = 9005s → 9,005,000,000,000ns
        // + 250,000,000ns = 9,005,250,000,000
        assertEquals(9_005_250_000_000, diff)
    }

    @Test
    fun `test getMilliSecondDifference returns correct total milliseconds`() {
        val diff = timeA.getMilliSecondDifference(timeB)
        // 9005s → 9,005,000ms + 250ms = 9,005,250
        assertEquals(9_005_250, diff)
    }

    @Test
    fun `test getSecondDifference returns correct seconds`() {
        val diff = timeA.getSecondDifference(timeB)
        assertEquals(9005, diff)
    }

    @Test
    fun `test getMinuteDifference returns correct minutes`() {
        val diff = timeA.getMinuteDifference(timeB)
        assertEquals(150, diff)
    }

    @Test
    fun `test getHourDifference returns correct hours`() {
        val diff = timeA.getHourDifference(timeB)
        assertEquals(2, diff)
    }

    @Test
    fun `test reversed time returns negative differences`() {
        val reversedNano = timeB.getNanoSecondDifference(timeA)
        val reversedMilli = timeB.getMilliSecondDifference(timeA)
        val reversedSec = timeB.getSecondDifference(timeA)
        val reversedMin = timeB.getMinuteDifference(timeA)
        val reversedHour = timeB.getHourDifference(timeA)

        assertEquals(-9_005_250_000_000, reversedNano)
        assertEquals(-9_005_250, reversedMilli)
        assertEquals(-9005, reversedSec)
        assertEquals(-150, reversedMin)
        assertEquals(-2, reversedHour)
    }

    @Test
    fun `test identical times return zero for all differences`() {
        val time = LocalTime(8, 0, 0, 0)
        assertEquals(0, time.getNanoSecondDifference(time))
        assertEquals(0, time.getMilliSecondDifference(time))
        assertEquals(0, time.getSecondDifference(time))
        assertEquals(0, time.getMinuteDifference(time))
        assertEquals(0, time.getHourDifference(time))
    }
}