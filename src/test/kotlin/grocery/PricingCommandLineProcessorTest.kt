package grocery

import org.junit.Assert
import org.junit.Test
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader

class PricingCommandLineProcessorTest {
    val target = PricingCommandLineProcessor()

    @Test
    fun testPrice() {
        val expectedPrice = 123456
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