import kotlinxdatetimefun.isLeapYear
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class InternalUtilTest {

    @Test
    fun `years divisible by 4 but not 100 are leap years`() {
        assertTrue(isLeapYear(2024))
        assertTrue(isLeapYear(1996))
        assertTrue(isLeapYear(2004))
    }

    @Test
    fun `years not divisible by 4 are not leap years`() {
        assertFalse(isLeapYear(2023))
        assertFalse(isLeapYear(2019))
        assertFalse(isLeapYear(2101))
    }

    @Test
    fun `years divisible by 100 but not 400 are not leap years`() {
        assertFalse(isLeapYear(1900))
        assertFalse(isLeapYear(1800))
        assertFalse(isLeapYear(2100))
    }

    @Test
    fun `years divisible by 400 are leap years`() {
        assertTrue(isLeapYear(1600))
        assertTrue(isLeapYear(2000))
        assertTrue(isLeapYear(2400))
    }

    @Test
    fun `test negative and zero years`() {
        assertTrue(isLeapYear(0))        // 0 is divisible by 400
        assertFalse(isLeapYear(-1))      // Not divisible by 4
        assertTrue(isLeapYear(-4))       // Leap year
        assertFalse(isLeapYear(-100))    // Not leap year
        assertTrue(isLeapYear(-400))     // Leap year
    }
}