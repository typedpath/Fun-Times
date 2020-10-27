package grocery

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.slot
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader

class PricingCommandLineProcessorTest {

   @Before
   fun setUp() = MockKAnnotations.init(this)

   @MockK
   var pricingCalculator = mockk<PricingCalculator>()

    val target = PricingCommandLineProcessor(pricingCalculator, CurrentStock.all)

    @Test
    fun testPrice() {
        val expectedPrice = 123456
        val slot = slot<List<ItemLine>>()
        every { pricingCalculator.priceInPence(capture(slot)) } returns expectedPrice
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
    target.run(inputStream, outputStream)
    val outputString = output.toString()
    Assert.assertEquals(expectedPrice.toString(),outputString.trim() )
    }
}