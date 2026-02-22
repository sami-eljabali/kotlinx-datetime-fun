package localdate

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinxdatetimefun.localdate.extensions.atEndOfDay
import kotlinxdatetimefun.localdate.extensions.atStartOfDay
import kotlinxdatetimefun.localdate.extensions.getLast
import kotlinxdatetimefun.localdate.extensions.getNext
import kotlinxdatetimefun.localdate.extensions.minusDays
import kotlinxdatetimefun.localdate.extensions.minusMonths
import kotlinxdatetimefun.localdate.extensions.minusYears
import kotlinxdatetimefun.localdate.extensions.plusDays
import kotlinxdatetimefun.localdate.extensions.plusMonths
import kotlinxdatetimefun.localdate.extensions.plusYears
import kotlinxdatetimefun.localtime.MAX
import kotlinxdatetimefun.localtime.MIN
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LocalDateMutatingExtensionsTest {

    @Test
    fun `test minusMonths with negative months`() {
        val date = LocalDate(2023, 1, 31)
        val result = date.minusMonths(-1) // should be same as plus
        assertEquals(LocalDate(2023, 2, 28), result)
    }

    @Test
    fun `test plusMonths with positive months`() {
        val date = LocalDate(2023, 1, 31)
        val result = date.plusMonths(1)
        assertEquals(LocalDate(2023, 2, 28), result)
    }

    @Test
    fun `test minusMonths with positive months`() {
        val date = LocalDate(2023, 1, 31)
        val result = date.minusMonths(1)
        assertEquals(LocalDate(2022, 12, 31), result)
    }

    @Test
    fun `test plusMonths with negative months`() {
        val date = LocalDate(2023, 1, 31)
        val result = date.plusMonths(-1) // should be same as minus
        assertEquals(LocalDate(2022, 12, 31), result)
    }

    @Test
    fun `test minusYears with positive years`() {
        val date = LocalDate.parse("2023-01-15")
        val result = date.minusYears(1)
        assertEquals(LocalDate.parse("2022-01-15"), result)
    }

    @Test
    fun `test minusYears with zero years`() {
        val date = LocalDate.parse("2023-01-15")
        val result = date.minusYears(0)
        assertEquals(date, result)
    }

    @Test
    fun `test minusYears with leap year`() {
        val date = LocalDate.parse("2020-02-29")
        val result = date.minusYears(4)
        assertEquals(LocalDate.parse("2016-02-29"), result)
    }

    @Test
    fun `test minusYears with negative years`() {
        val date = LocalDate.parse("2023-01-15")
        val result = date.minusYears(-1)
        assertEquals(LocalDate.parse("2024-01-15"), result)
    }

    @Test
    fun `test plusYears with positive years`() {
        val date = LocalDate.parse("2023-01-15")
        val result = date.plusYears(1)
        assertEquals(LocalDate.parse("2024-01-15"), result)
    }

    @Test
    fun `test plusYears with zero years`() {
        val date = LocalDate.parse("2023-01-15")
        val result = date.plusYears(0)
        assertEquals(date, result)
    }

    @Test
    fun `test plusYears with leap year`() {
        val date = LocalDate.parse("2020-02-29")
        val result = date.plusYears(1)
        assertEquals(LocalDate.parse("2021-02-28"), result)
    }

    @Test
    fun `test plusYears with negative years`() {
        val date = LocalDate.parse("2023-01-15")
        val result = date.plusYears(-1)
        assertEquals(LocalDate.parse("2022-01-15"), result)
    }

    @Test
    fun `test edge case for month operations`() {
        val date = LocalDate(2023, 3, 31)
        // Adding 1 month should go to April 30
        assertEquals(LocalDate(2023, 4, 30), date.plusMonths(1))
        // Subtracting 1 month should go to February 28
        assertEquals(LocalDate(2023, 2, 28), date.minusMonths(1))
    }

    @Test
    fun `test edge case for year operations`() {
        val date = LocalDate(2020, 2, 29)
        // Adding 1 year to leap day should go to Feb 28
        assertEquals(LocalDate(2021, 2, 28), date.plusYears(1))
        // Subtracting 4 years should go to another leap day
        assertEquals(LocalDate(2016, 2, 29), date.minusYears(4))
    }

    @Test
    fun `minusDays subtracts the correct number of days`() {
        // given
        val date = LocalDate(2021, 9, 15)  // Wednesday
        // when
        val result = date.minusDays(10)
        // then: 2021-09-15 minus 10 days is 2021-09-05
        assertEquals(LocalDate(2021, 9, 5), result)
    }

    @Test
    fun `plusDays adds the correct number of days`() {
        // given
        val date = LocalDate(2021, 9, 15)  // Wednesday
        // when
        val result = date.plusDays(10)
        // then: 2021-09-15 plus 10 days is 2021-09-25
        assertEquals(LocalDate(2021, 9, 25), result)
    }

    @Test
    fun `getLast returns the same day when countingInThisDay is true and current day matches target`() {
        // given
        val date = LocalDate(2021, 9, 15)  // Wednesday
        // when: counting in the current day, if the target day is Wednesday, we get the same date.
        val lastWednesday = date.getLast(DayOfWeek.WEDNESDAY, countingInThisDay = true)
        // then
        assertEquals(date, lastWednesday)
    }

    @Test
    fun `getLast returns the previous occurrence when countingInThisDay is false and current day matches target`() {
        // given
        val date = LocalDate(2021, 9, 15)  // Wednesday
        // when: not counting the current day, even though it's Wednesday, should get previous Wednesday.
        val lastWednesday = date.getLast(DayOfWeek.WEDNESDAY, countingInThisDay = false)
        // then: previous Wednesday is 7 days before 2021-09-15 → 2021-09-08
        assertEquals(LocalDate(2021, 9, 8), lastWednesday)
    }

    @Test
    fun `getLast returns the most recent occurrence for a different target day`() {
        // given
        val date = LocalDate(2021, 9, 15)  // Wednesday
        // when: Looking for last Monday.
        val lastMonday = date.getLast(DayOfWeek.MONDAY)
        // then: The Monday before Wednesday 2021-09-15 is 2021-09-13.
        assertEquals(LocalDate(2021, 9, 13), lastMonday)
    }

    @Test
    fun `getNext returns the same day when countingInThisDay is true and current day matches target`() {
        // given
        val date = LocalDate(2021, 9, 15)  // Wednesday
        // when: counting in this day for Wednesday should return same date.
        val nextWednesday = date.getNext(DayOfWeek.WEDNESDAY, countingInThisDay = true)
        // then
        assertEquals(date, nextWednesday)
    }

    @Test
    fun `getNext returns the next occurrence when countingInThisDay is false and current day matches target`() {
        // given
        val date = LocalDate(2021, 9, 15)  // Wednesday
        // when: not counting the current day; even though it's Wednesday, should return the next Wednesday.
        val nextWednesday = date.getNext(DayOfWeek.WEDNESDAY, countingInThisDay = false)
        // then: next Wednesday is 7 days after 2021-09-15 → 2021-09-22
        assertEquals(LocalDate(2021, 9, 22), nextWednesday)
    }

    @Test
    fun `getNext returns the next occurrence for a different target day`() {
        // given
        val date = LocalDate(2021, 9, 15)  // Wednesday
        // when: Looking for next Friday.
        val nextFriday = date.getNext(DayOfWeek.FRIDAY)
        // then: The Friday following 2021-09-15 (Wednesday) is 2021-09-17.
        assertEquals(LocalDate(2021, 9, 17), nextFriday)
    }

    @Test
    fun `atStartOfDay returns LocalDateTime with time MIN`() {
        // given
        val date = LocalDate(2021, 9, 15)
        // when
        val startOfDay = date.atStartOfDay()
        // then: It should combine the date with LocalTime.MIN.
        assertEquals(LocalDateTime(date, LocalTime.MIN), startOfDay)
    }

    @Test
    fun `atEndOfDay returns LocalDateTime with time MAX`() {
        // given
        val date = LocalDate(2021, 9, 15)
        // when
        val endOfDay = date.atEndOfDay()
        // then: It should combine the date with LocalTime.MAX.
        assertEquals(LocalDateTime(date, LocalTime.MAX), endOfDay)
    }
}