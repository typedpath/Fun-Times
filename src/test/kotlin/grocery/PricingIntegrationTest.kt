package grocery

import io.mockk.every
import io.mockk.slot
import org.junit.Assert
import org.junit.Test
import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader
import java.time.LocalDate

/**
 * test for integration of command line processor and calculator
 */
class PricingIntegrationTest {


    val target = PricingCommandLineProcessor(PricingCalculator(LocalDate.now()), CurrentStock.all)

    @Test
    fun testPrice() {
        val pricingDate = LocalDate.now()
        val input =
            (
"""
add 3 soup
add 5 apples
price
add 1 bread
add 1 apples
price
""".trim()
                    )
        val inputStream = BufferedReader(StringReader(input))
        val output = ByteArrayOutputStream()
        val outputStream = PrintStream(output)
        target.run(inputStream, outputStream, pricingDate)
        val outputString = output.toString()
        println(outputString)
        val prices = outputString.split("\r\n")
        Assert.assertEquals("245", prices[0])
        Assert.assertEquals("90", prices[1])
    }
}