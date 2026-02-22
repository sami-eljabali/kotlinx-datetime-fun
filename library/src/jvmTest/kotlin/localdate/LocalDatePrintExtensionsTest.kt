package localdate

import kotlinx.datetime.LocalDate
import kotlinxdatetimefun.localdate.extensions.print
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LocalDatePrintExtensionsTest {

    @Test
    fun `print formats date using dd slash MM slash yyyy pattern`() {
        // given
        val date = LocalDate(2021, 9, 15)
        val pattern = "dd/MM/yyyy"
        // when
        val formatted = date.print(pattern)
        // then
        assertEquals("15/09/2021", formatted)
    }

    @Test
    fun `print formats date using yyyy dash MM dash dd pattern`() {
        // given
        val date = LocalDate(2021, 9, 15)
        val pattern = "yyyy-MM-dd"
        // when
        val formatted = date.print(pattern)
        // then
        assertEquals("2021-09-15", formatted)
    }

    @Test
    fun `print formats date with custom literal text in pattern`() {
        // given
        val date = LocalDate(2021, 9, 15)
        // Using literal text in the pattern by enclosing it in single quotes
        val pattern = "yyyy 'Year, Month:' MM"
        // when
        val formatted = date.print(pattern)
        // then
        assertEquals("2021 Year, Month: 09", formatted)
    }
}