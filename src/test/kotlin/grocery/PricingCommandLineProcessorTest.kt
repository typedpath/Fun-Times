package grocery

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
import net.bytebuddy.asm.Advice
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader
import java.time.LocalDate

/**
 * unit test for the command line processor
 */
class PricingCommandLineProcessorTest {

   @Before
   fun setUp() = MockKAnnotations.init(this)

   @MockK
   var pricingCalculator = mockk<PricingCalculator>()

    val target = PricingCommandLineProcessor(pricingCalculator, CurrentStock.all)

    @Test
    fun testPrice() {
        val expectedPrice = 123456
        val pricingDate = LocalDate.now()
        val slot = slot<List<ItemLine>>()
        val priceDateSlot = slot<LocalDate>()
        every { pricingCalculator.priceInPence(capture(slot), capture(priceDateSlot))  } returns expectedPrice
        val input =
(
"""add 3 soup
   add 5 apples
   price
""".trim()
)
    val inputStream = BufferedReader(StringReader(input))
    val output = ByteArrayOutputStream()
    val outputStream = PrintStream(output)
    target.run(inputStream, outputStream, pricingDate)
    val outputString = output.toString()
    Assert.assertEquals(expectedPrice.toString(),outputString.trim() )
    Assert.assertEquals(pricingDate, priceDateSlot.captured )
    }
}